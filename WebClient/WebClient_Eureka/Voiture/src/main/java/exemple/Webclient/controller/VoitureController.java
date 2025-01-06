package exemple.Webclient.controller;

import exemple.Webclient.entities.Client;
import exemple.Webclient.entities.Voiture;
import exemple.Webclient.service.VoitureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    private final VoitureService voitureService;
    private final WebClient webClient;

    public VoitureController(VoitureService voitureService, WebClient.Builder webClientBuilder) {
        this.voitureService = voitureService;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build(); // URL de votre service client
    }

    @GetMapping
    public ResponseEntity<List<Voiture>> getAllVoitures() {
        List<Voiture> voitures = voitureService.findAll();
        voitures.forEach(voiture -> {
            Client client = getClient(voiture.getId_client());
            voiture.setClient(client);
        });
        return ResponseEntity.ok(voitures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voiture> getVoitureById(@PathVariable Long id) {
        Optional<Voiture> voitureOpt = voitureService.findById(id);
        if (voitureOpt.isPresent()) {
            Voiture voiture = voitureOpt.get();
            Client client = getClient(voiture.getId_client());
            voiture.setClient(client);
            return ResponseEntity.ok(voiture);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{clientId}")
    public ResponseEntity<Voiture> saveVoiture(@PathVariable("clientId") Long clientId, @RequestBody Voiture voiture) {
        Client client = getClient(clientId);
        if (client != null) {
            voiture.setId_client(clientId);
            Voiture savedVoiture = voitureService.save(voiture);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedVoiture);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Client getClient(Long clientId) {
        return webClient.get()
                .uri("/client/{id}", clientId)
                .retrieve()
                .bodyToMono(Client.class)
                .block();  // Utilisez block() pour obtenir le résultat de manière synchrone
    }
}