package com.example.myexamen.clients;

import com.example.myexamen.chambres.Chambres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired private ClientsRepository repo;

    public List<Clients> listAll(){
        return (List<Clients>) repo.findAll();
    }
    public void save(Clients client){
        repo.save(client);
    }
    public void insererclient(String nom,String postnom,String prenom,String genre,String nationalite,
                              String profession,String etatcivil,String mail,String adresse,String telephone){
        Clients c=new Clients();
        c.setNom(nom);
        c.setPostnom(postnom);
        c.setPrenom(prenom);
        c.setGenre(genre);
        c.setNationalite(nationalite);
        c.setProfession(profession);
        c.setEtatcivil(etatcivil);
        c.setMail(mail);
        c.setAdresse(adresse);
        c.setTelephone(telephone);
        repo.save(c);
    }
    public Clients get(Integer id) throws RuntimeException{
        Optional<Clients> optional = repo.findById(id);
        Clients client = null;
        if(optional.isPresent()){
            return optional.get();
        }
            throw new RuntimeException(" ce client n'existe pas avec l'id ::" +id);
        }
    public void countClient(){
        long id = repo.count();
    }
}
