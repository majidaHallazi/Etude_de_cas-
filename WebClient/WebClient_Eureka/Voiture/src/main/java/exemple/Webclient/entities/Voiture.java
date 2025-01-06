package exemple.Webclient.entities;

import jakarta.persistence.*;

@Entity
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ajoutez une stratégie de génération
    private Long id;
    private String marque;
    private String matricule;
    private String model;
    private Long id_client; // Changez int en Long pour correspondre à Client

    @Transient
    private Client client; // Client associé

    // Constructeurs
    public Voiture() {
    }

    public Voiture(Long id, String marque, String matricule, String model, Long id_client) { // Changez int en Long
        this.id = id;
        this.marque = marque;
        this.matricule = matricule;
        this.model = model;
        this.id_client = id_client; // Changez int en Long
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getId_client() { // Changez int en Long
        return id_client; // Changez int en Long
    }

    public void setId_client(Long id_client) { // Changez int en Long
        this.id_client = id_client; // Changez int en Long
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}