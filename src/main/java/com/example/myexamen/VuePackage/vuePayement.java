package com.example.myexamen.VuePackage;

import javax.persistence.*;

@Entity
@Table(name = "vuepayement")
public class vuePayement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String client;
    private String chambre;
    private float prix;
    public String debut;
    public String fin;
    private int nombre_jours;
    private int Jours_restant;
    private float montant;
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getChambre() {
        return chambre;
    }

    public void setChambre(String chambre) {
        this.chambre = chambre;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public int getNombre_jours() {
        return nombre_jours;
    }

    public void setNombre_jours(int nombre_jours) {
        this.nombre_jours = nombre_jours;
    }

    public int getJours_restant() {
        return Jours_restant;
    }

    public void setJours_restant(int jours_restant) {
        Jours_restant = jours_restant;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
