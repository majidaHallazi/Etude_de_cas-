package com.example.voiture;

import com.example.voiture.entities.Car;
import com.example.voiture.repositories.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.util.logging.Logger;

@SpringBootApplication
public class VoitureApplication {

	private static final Logger logger = Logger.getLogger(VoitureApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(VoitureApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(5000);
		requestFactory.setReadTimeout(5000);

		return new RestTemplate(requestFactory);
	}

	@Bean
	CommandLineRunner initData(CarRepository carRepository) {
		return args -> {
			logger.info("Initializing Car data...");
			carRepository.save(new Car("BMW", "14 R 5288", 5L));
			carRepository.save(new Car("Audi", "34 T 6732", 6L));
			carRepository.save(new Car("Mercedes", "44 R 6712", 7L));
			logger.info("Car data initialized successfully.");
		};
	}
}
