package com.example.myexamen.VuePackage;

import javax.persistence.*;
// r.id,c.prenom AS client,ch.name AS chambre,R.montant AS prix,r.debut,r.fin,(SELECT DATEDIFF(r.fin,r.debut))AS nombre_jours,(SELECT DATEDIFF(r.fin,CURDATE()))AS Jours_restant,r.montant*(SELECT DATEDIFF(r.fin,r.debut))AS montant FROM `reservations` AS r INNER JOIN clients AS c ON r.idclient=c.id INNER JOIN chambres AS ch ON r.idchambre=ch.id WHERE ch.id=?;",nativeQuery =
@Entity
@Table(name = "vue")
public class vue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String client;
    private String chambre;
    private float prix;
    public String debut;
    public String fin;
    private int nombre_jours;

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

    private int Jours_restant;
    private float montant;



}
