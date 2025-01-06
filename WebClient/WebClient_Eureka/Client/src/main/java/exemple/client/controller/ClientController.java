package exemple.client.controller;

import exemple.client.entities.Client;
import exemple.client.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    private final ClientRepository clientRepository;
    private final WebClient webClient;

    public ClientController(ClientRepository clientRepository, WebClient.Builder webClientBuilder) {
        this.clientRepository = clientRepository;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build(); // Remplacez par l'URL de l'API cible
    }

    @GetMapping("/clients")
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/client/{id}")
    public Optional<Client> getClient(@PathVariable Long id) {
        return clientRepository.findById(id);
    }
}