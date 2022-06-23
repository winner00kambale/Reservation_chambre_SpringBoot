package com.example.myexamen.VuePackage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface vueRepository extends CrudRepository<vue,Integer> {
    @Query(value = "SELECT r.id,c.prenom AS client,ch.name AS chambre,R.montant AS prix,r.debut,r.fin,(SELECT DATEDIFF(r.fin,r.debut))AS nombre_jours,(SELECT DATEDIFF(r.fin,CURDATE()))AS Jours_restant,r.montant*(SELECT DATEDIFF(r.fin,r.debut))AS montant FROM `reservations` AS r INNER JOIN clients AS c ON r.idclient=c.id INNER JOIN chambres AS ch ON r.idchambre=ch.id WHERE ch.id=?;",nativeQuery = true)
    List<vue> test(int id);
    @Query(value = "SELECT r.id,c.prenom AS client,ch.name AS chambre,R.montant AS prix,r.debut,r.fin,(SELECT DATEDIFF(r.fin,r.debut))AS nombre_jours,(SELECT DATEDIFF(r.fin,CURDATE()))AS Jours_restant,r.montant*(SELECT DATEDIFF(r.fin,r.debut))AS montant FROM `reservations` AS r INNER JOIN clients AS c ON r.idclient=c.id INNER JOIN chambres AS ch ON r.idchambre=ch.id",nativeQuery = true)
    List<vue> All();
}
