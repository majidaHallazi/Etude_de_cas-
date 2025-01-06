package com.example.voiture.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String matricule;
    private Long clientId;

    public Car() {
    }

    public Car(String brand, String matricule, Long clientId) {
        this.brand = brand;
        this.matricule = matricule;
        this.clientId = clientId;
    }

    public Car(Long id, String brand, String matricule, Long clientId) {
        this.id = id;
        this.brand = brand;
        this.matricule = matricule;
        this.clientId = clientId;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", matricule='" + matricule + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
