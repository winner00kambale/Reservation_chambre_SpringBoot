package com.example.myexamen.chambres;

import javax.persistence.*;

@Entity
@Table(name = "chambres")
public class Chambres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name",nullable = false, unique = true, length = 45)
    private String name;
    @Column(name = "designation")
    private String designation;
    @Column(name = "prix")
    private float prix;
    @Column(name = "devise")
    private String devise;
    @Column(name = "etat")
    private String etat;
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
