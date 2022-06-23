package com.example.myexamen.reservations;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ReservationRepository extends CrudRepository<Reservations, Integer> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE chambres SET etat='occupee' WHERE id=?",nativeQuery = true)
    public void UpdateChambre(int id);
    @Query(value = "SELECT id FROM chambres WHERE name=?",nativeQuery = true)
    public int GetName(String id);
    @Query(value = "SELECT mail FROM clients WHERE id=?",nativeQuery = true)
    public String getMailClient(int prenom);
    @Query(value = "SELECT prenom FROM clients WHERE id=?",nativeQuery = true)
    public String getprenomClient(int idc);
}
