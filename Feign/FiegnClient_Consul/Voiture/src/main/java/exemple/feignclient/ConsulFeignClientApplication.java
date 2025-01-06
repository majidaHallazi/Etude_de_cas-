package exemple.feignclient;

import exemple.feignclient.entities.Voiture;
import exemple.feignclient.repository.VoitureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class ConsulFeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulFeignClientApplication.class, args);
    }
    /*@Bean
    CommandLineRunner initDatabase(VoitureRepository voitureRepository) {
        return args -> {
            Voiture voiture1 = new Voiture(null, "Toyota", "ABC123", "Corolla", 1L);
            Voiture voiture2 = new Voiture(null, "Honda", "XYZ456", "Civic", 2L);
            Voiture voiture3 = new Voiture(null, "Ford", "LMN789", "Focus", 1L);
            Voiture voiture4 = new Voiture(null, "Chevrolet", "DEF012", "Impala", 3L);

            voitureRepository.saveAll(Arrays.asList(voiture1, voiture2, voiture3, voiture4));
        };
    }*/
}