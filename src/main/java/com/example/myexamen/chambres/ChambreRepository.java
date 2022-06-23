package com.example.myexamen.chambres;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChambreRepository extends CrudRepository<Chambres, Integer> {
    public Long countById(Integer id);
    public List<Chambres> findByEtat(String etat);
}
