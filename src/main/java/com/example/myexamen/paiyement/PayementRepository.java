package com.example.myexamen.paiyement;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface PayementRepository extends CrudRepository<Paiyement,Integer> {
    @Query(value = "SELECT COUNT(*) FROM payement WHERE Date(date)=CURDATE()",nativeQuery = true)
    public int allPayementByDate();
}
