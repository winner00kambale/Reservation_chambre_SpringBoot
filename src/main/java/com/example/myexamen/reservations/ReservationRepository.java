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
//    @Query(value = "SELECT * FROM aff_detail WHERE chambre=?",nativeQuery = true)
  //  public String getDetail(String name);
}
