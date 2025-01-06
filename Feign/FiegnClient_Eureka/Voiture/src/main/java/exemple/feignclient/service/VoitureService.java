package exemple.feignclient.service;

import exemple.feignclient.entities.Voiture;
import exemple.feignclient.repository.VoitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoitureService {

    private final VoitureRepository voitureRepository;

    public VoitureService(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }

    public List<Voiture> findAll() {
        return voitureRepository.findAll();
    }

    public Optional<Voiture> findById(Long id) {
        return voitureRepository.findById(id);
    }

    public Voiture save(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

}