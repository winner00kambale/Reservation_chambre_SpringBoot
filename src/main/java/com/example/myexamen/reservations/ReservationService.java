package com.example.myexamen.reservations;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ReservationService {
    @Autowired TestRepository testRepo;
    @Autowired private ReservationRepository repo;
    public List<Reservations> listAll(){
    return (List<Reservations>) repo.findAll();
}

    public void insererReservation(int idcli, int idchambre, float prix, String devise, String debut, String fin){
        Reservations r = new Reservations();
        r.setIdclient(idcli);
        r.setIdchambre(idchambre);
        r.setMontant(prix);
        r.setDevise(devise);
        r.setDebut(debut);
        r.setFin(fin);
        repo.save(r);
    }
    public void addTest(){
        test t = new test();
        t.setName("welcome");
        testRepo.save(t);
    }
}
