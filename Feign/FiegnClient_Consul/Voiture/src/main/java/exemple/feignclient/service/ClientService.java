package exemple.feignclient.service;

import exemple.feignclient.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service",url = "http://localhost:8081") // Assurez-vous que le nom correspond à celui enregistré dans Consul
public interface ClientService {
    @GetMapping(path = "/client/{id}")
    Client getClient(@PathVariable("id") Long id);// Utilisez Long si votre ID est de type Long
}