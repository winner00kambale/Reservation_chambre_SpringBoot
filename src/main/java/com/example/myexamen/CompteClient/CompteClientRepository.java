package com.example.myexamen.CompteClient;


import com.example.myexamen.clients.Clients;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompteClientRepository extends CrudRepository<Clients,Integer> {
    @Query(value = "select * from clients where prenom=? and mail =?",nativeQuery = true)
    Optional<Clients> testCompte(String prenom, String mail);
    @Query(value = "select prenom from clients where mail =?",nativeQuery = true)
    public String Getprenom(String prenom);
}
