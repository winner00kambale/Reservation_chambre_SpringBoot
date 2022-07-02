package com.example.myexamen.paiyement;

import com.example.myexamen.chambres.Chambres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayementService {
    @Autowired private PayementRepository payementRepository;

    public void SavePayement(int reservation,float montant,int jours,String devise){
        Paiyement paiyement = new Paiyement();
        paiyement.setRefReservation(reservation);
        paiyement.setMontant(montant);
        paiyement.setNombrejour(jours);
        paiyement.setDevise(devise);
        payementRepository.save(paiyement);
    }
}
