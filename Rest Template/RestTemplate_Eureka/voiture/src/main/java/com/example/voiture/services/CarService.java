package com.example.voiture.services;

import com.example.voiture.entities.Car;
import com.example.voiture.entities.Client;
import com.example.voiture.exceptions.CarNotFoundException;
import com.example.voiture.models.CarResponse;
import com.example.voiture.repositories.CarRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CarService {

    private static final Logger logger = Logger.getLogger(CarService.class.getName());

    @Value("${client.service.url:http://localhost:8081/client/}")
    private String clientServiceUrl;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<CarResponse> findAll() {
        return carRepository.findAll()
                .stream()
                .map(this::mapToCarResponse)
                .collect(Collectors.toList());
    }

    public CarResponse findById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found with ID: " + id));
        return mapToCarResponse(car);
    }

    @CircuitBreaker(name = "clientService", fallbackMethod = "fallbackClientResponse")
    private CarResponse mapToCarResponse(Car car) {
        Client client = restTemplate.getForObject(clientServiceUrl + car.getClientId(), Client.class);
        return new CarResponse(car.getId(), car.getBrand(), car.getMatricule(), client);
    }

    private CarResponse fallbackClientResponse(Car car, Throwable t) {
        logger.warning("Client service is unavailable. Returning default response for car ID: " + car.getId());
        Client defaultClient = new Client();
        defaultClient.setId(car.getClientId());
        defaultClient.setNom("Unknown Client");
        defaultClient.setAge(0);
        return new CarResponse(car.getId(), car.getBrand(), car.getMatricule(), defaultClient);
    }
}
