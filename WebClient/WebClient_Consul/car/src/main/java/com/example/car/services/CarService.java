package com.example.car.services;

import com.example.car.entities.Car;
import com.example.car.entities.Client;
import com.example.car.models.CarResponse;
import com.example.car.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final WebClient webClient;

    private final String CLIENT_SERVICE_URL = "http://localhost:8888/client/api/client";

    @Autowired
    public CarService(CarRepository carRepository, WebClient.Builder webClientBuilder) {
        this.carRepository = carRepository;
        this.webClient = webClientBuilder.baseUrl(CLIENT_SERVICE_URL).build();
    }

    public Mono<List<CarResponse>> findAll() {
        List<Car> cars = carRepository.findAll();

        // Fetch all clients reactively
        return webClient
                .get()
                .retrieve()
                .bodyToFlux(Client.class) // Fetch all clients as Flux
                .collectList() // Collect Flux<Client> into List<Client>
                .map(clients -> cars.stream()
                        .map(car -> mapToCarResponse(car, clients))
                        .collect(Collectors.toList()) // Map cars to CarResponse
                );
    }

    /**
     * Find a car by ID and map it to CarResponse reactively.
     */
    public Mono<CarResponse> findById(Long id) {
        return Mono.justOrEmpty(carRepository.findById(id)) // Wrap the car entity in a Mono
                .switchIfEmpty(Mono.error(new Exception("Invalid Car ID"))) // Handle empty result
                .flatMap(car ->
                        webClient
                                .get()
                                .uri("/{id}", car.getClient_id())
                                .retrieve()
                                .bodyToMono(Client.class) // Fetch the client reactively
                                .map(client -> mapToCarResponse(car, client)) // Map to CarResponse
                );
    }

    /**
     * Helper method to map a Car entity to CarResponse.
     */
    private CarResponse mapToCarResponse(Car car, List<Client> clients) {
        Client foundClient = clients.stream()
                .filter(client -> client.getId().equals(car.getClient_id()))
                .findFirst()
                .orElse(null);

        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .client(foundClient)
                .matricue(car.getMatricule())
                .model(car.getModel())
                .build();
    }

    private CarResponse mapToCarResponse(Car car, Client client) {
        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .client(client)
                .matricue(car.getMatricule())
                .model(car.getModel())
                .build();
    }
}

