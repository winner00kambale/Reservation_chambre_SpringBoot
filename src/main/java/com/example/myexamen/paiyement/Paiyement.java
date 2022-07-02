package com.example.myexamen.paiyement;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payement")
public class Paiyement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int refReservation;
    private float montant;
    @Column(name = "date",nullable = false,updatable = false,insertable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;
    private int nombrejour;
    private String devise;

    public int getNombrejour() {
        return nombrejour;
    }

    public void setNombrejour(int nombrejour) {
        this.nombrejour = nombrejour;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRefReservation() {
        return refReservation;
    }

    public void setRefReservation(int refReservation) {
        this.refReservation = refReservation;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
