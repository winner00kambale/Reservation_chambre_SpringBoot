package com.example.myexamen.VuePackage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface vueRepository extends CrudRepository<vue,Integer> {
    @Query(value = "SELECT r.id,c.prenom AS client,ch.name AS chambre,R.montant AS prix,r.debut,r.fin,(SELECT DATEDIFF(r.fin,r.debut))AS nombre_jours,(SELECT DATEDIFF(r.fin,CURDATE()))AS Jours_restant,r.montant*(SELECT DATEDIFF(r.fin,r.debut))AS montant FROM `reservations` AS r INNER JOIN clients AS c ON r.idclient=c.id INNER JOIN chambres AS ch ON r.idchambre=ch.id WHERE ch.id=? AND (SELECT DATEDIFF(r.fin,CURDATE())) > 0;",nativeQuery = true)
    List<vue> test(int id);
    @Query(value = "SELECT r.id,c.prenom AS client,ch.name AS chambre,R.montant AS prix,r.debut,r.fin,(SELECT DATEDIFF(r.fin,r.debut))AS nombre_jours,(SELECT DATEDIFF(r.fin,CURDATE()))AS Jours_restant,r.montant*(SELECT DATEDIFF(r.fin,r.debut))AS montant FROM `reservations` AS r INNER JOIN clients AS c ON r.idclient=c.id INNER JOIN chambres AS ch ON r.idchambre=ch.id",nativeQuery = true)
    List<vue> All();
    @Query(value = "SELECT CONCAT('Nom client(e) : ' ,c.prenom , ' reserve la chambre ', ch.name ,' au prix de ', ch.prix, ' USD par jour, pour la periode allant du ', r.debut,' au ', r.fin,' faisant donc ', (SELECT DATEDIFF(r.fin,r.debut)),' jours. Le montant Total paye est de ', r.montant*(SELECT DATEDIFF(r.fin,r.debut))) FROM `reservations` AS r INNER JOIN clients AS c ON r.idclient=c.id INNER JOIN chambres AS ch ON r.idchambre=ch.id WHERE r.id=?;",nativeQuery = true)
    public String AllbyId(int id);
    @Query(value = "SELECT r.id,c.prenom AS client,ch.name AS chambre,R.montant AS prix,r.debut,r.fin,(SELECT DATEDIFF(r.fin,r.debut))AS nombre_jours,(SELECT DATEDIFF(r.fin,CURDATE()))AS Jours_restant,r.montant*(SELECT DATEDIFF(r.fin,r.debut))AS montant FROM `reservations` AS r INNER JOIN clients AS c ON r.idclient=c.id INNER JOIN chambres AS ch ON r.idchambre=ch.id WHERE r.debut = ?",nativeQuery = true)
    List<vue> AllByDate(String date);
    @Query(value = "SELECT r.id,c.prenom AS client,ch.name AS chambre,R.montant AS prix,r.debut,r.fin,(SELECT DATEDIFF(r.fin,r.debut))AS nombre_jours,(SELECT DATEDIFF(r.fin,CURDATE()))AS Jours_restant,r.montant*(SELECT DATEDIFF(r.fin,r.debut))AS montant FROM `reservations` AS r INNER JOIN clients AS c ON r.idclient=c.id INNER JOIN chambres AS ch ON r.idchambre=ch.id WHERE r.id=?",nativeQuery = true)
    List<vue> AllReserById(int id);
}
