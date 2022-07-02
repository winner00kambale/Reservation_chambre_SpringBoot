package com.example.myexamen.VuePackage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface vuePayementRepository extends CrudRepository<vuePayement,Integer> {
    @Query(value = "SELECT r.id,c.prenom AS client,ch.name AS chambre,R.montant AS prix,r.debut,r.fin,(SELECT DATEDIFF(r.fin,r.debut))AS nombre_jours,(SELECT DATEDIFF(r.fin,CURDATE()))AS Jours_restant,r.montant*(SELECT DATEDIFF(r.fin,r.debut))AS montant,p.date FROM `reservations` AS r INNER JOIN clients AS c ON r.idclient=c.id INNER JOIN chambres AS ch ON r.idchambre=ch.id LEFT JOIN payement AS p on r.id=p.ref_reservation WHERE r.id NOT IN(SELECT payement.ref_reservation FROM payement)",nativeQuery = true)
    List<vuePayement> allReservation();
    @Query(value = "SELECT r.id,c.prenom AS client,ch.name AS chambre,R.montant AS prix,r.debut,r.fin,(SELECT DATEDIFF(r.fin,r.debut))AS nombre_jours,(SELECT DATEDIFF(r.fin,CURDATE()))AS Jours_restant,r.montant*(SELECT DATEDIFF(r.fin,r.debut))AS montant,p.date FROM `reservations` AS r INNER JOIN clients AS c ON r.idclient=c.id INNER JOIN chambres AS ch ON r.idchambre=ch.id LEFT JOIN payement AS p on r.id=p.ref_reservation WHERE r.id IN(SELECT payement.ref_reservation FROM payement)",nativeQuery = true)
    List<vuePayement> allReservationPayee();
}
