package com.example.myexamen.clients;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, name = "nom")
    private String nom;
    @Column(nullable = false, length = 50, name = "postnom")
    private String postnom;
    @Column(nullable = false, length = 50, name = "prenom")
    private String prenom;
    @Column(nullable = false, length = 6, name = "m")
    private String genre;
    @Column(nullable = false, length = 50, name = "nationalite")
    private String nationalite;
    @Column(nullable = false, length = 50, name = "profession")
    private String profession;
    @Column(nullable = false, length = 50, name = "etatcivil")
    private String etatcivil;
    @Column(nullable = false, unique = true, length = 45)
    private String mail;
    @Column(nullable = false, length = 100, name = "adresse")
    private String adresse;
    @Column(nullable = false, length = 100, name = "telephone",unique = true)
    private String telephone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPostnom() {
        return postnom;
    }

    public void setPostnom(String postnom) {
        this.postnom = postnom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEtatcivil() {
        return etatcivil;
    }

    public void setEtatcivil(String etatcivil) {
        this.etatcivil = etatcivil;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", postnom='" + postnom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", genre='" + genre + '\'' +
                ", nationalite='" + nationalite + '\'' +
                ", profession='" + profession + '\'' +
                ", etatcivil='" + etatcivil + '\'' +
                ", mail='" + mail + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
