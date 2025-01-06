package exemple.feignclient.controller;

import exemple.feignclient.entities.Client;
import exemple.feignclient.entities.Voiture;
import exemple.feignclient.service.ClientService;
import exemple.feignclient.service.VoitureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    private final VoitureService voitureService;
    private final ClientService clientService;

    public VoitureController(VoitureService voitureService, ClientService clientService) {
        this.voitureService = voitureService;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Voiture>> getAllVoitures() {
        List<Voiture> voitures = voitureService.findAll();
        voitures.forEach(voiture -> {

            Client client = clientService.getClient(voiture.getId_client()); // Assurez-vous que getId_client retourne un Long
            voiture.setClient(client); // Assurez-vous que setClient est défini dans Voiture
        });
        return ResponseEntity.ok(voitures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voiture> getVoitureById(@PathVariable Long id) {
        Optional<Voiture> voitureOpt = voitureService.findById(id);
        if (voitureOpt.isPresent()) {
            Voiture voiture = voitureOpt.get();
            Client client = clientService.getClient(voiture.getId_client());
            voiture.setClient(client);
            return ResponseEntity.ok(voiture);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{clientId}")
    public ResponseEntity<Voiture> saveVoiture(@PathVariable("clientId") Long clientId, @RequestBody Voiture voiture) {
        Client client = clientService.getClient(clientId); // Assurez-vous que getClient prend Long
        if (client != null) {
            voiture.setId_client(clientId); // Assurez-vous que id_client est du même type
            Voiture savedVoiture = voitureService.save(voiture);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedVoiture);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}