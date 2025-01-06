package com.example.voiture.models;

import com.example.voiture.entities.Client;

public class CarResponse {
    private Long id;
    private String brand;
    private String matricule;
    private Client client;

    public CarResponse(Long id, String brand, String matricule, Client client) {
        this.id = id;
        this.brand = brand;
        this.matricule = matricule;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "CarResponse{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", matricule='" + matricule + '\'' +
                ", client=" + client +
                '}';
    }
}
