package com.example.client.services;

import com.example.client.entities.Client;
import com.example.client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    private final WebClient webClient;


    public ClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) throws Exception {
        return clientRepository.findById(id)
                .orElseThrow(() -> new Exception("Invalid Client ID"));
    }

    public void addClient(Client client) {

        clientRepository.save(client);
    }
}
