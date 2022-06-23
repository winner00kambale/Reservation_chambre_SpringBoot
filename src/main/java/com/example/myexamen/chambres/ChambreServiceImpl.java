package com.example.myexamen.chambres;

import com.example.myexamen.clients.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.CallbackHandler;
import java.util.List;
import java.util.Optional;

@Service
public class ChambreServiceImpl {
    @Autowired private ChambreRepository chambreRepository;
    public List<Chambres> listAll(){return (List<Chambres>) chambreRepository.findAll();}
    public List<Chambres> listAllByEtat(){return chambreRepository.findByEtat("libre");}
    public List<Chambres> listByEtat(){return chambreRepository.findByEtat("occupee");}

    public void save(Chambres chambre){
        chambreRepository.save(chambre);
    }
    public void insererchambre(String name,String designation,float prix,String devise){Chambres c = new Chambres();
       c.setName(name);
       c.setDesignation(designation);
       c.setPrix(prix);
       c.setDevise(devise);
       c.setEtat("libre");
        chambreRepository.save(c);
    }
    public Chambres get(Integer id) throws ChambreNotFoundException {
        Optional<Chambres> result = chambreRepository.findById(id);
        if(result.isPresent()){
           return result.get();
        }
        throw new ChambreNotFoundException("Could not find room with id :" +id);
    }
    public void delete(Integer id) throws ChambreNotFoundException {
        Long count = chambreRepository.countById(id);
        if(count == null || count == 0){
            throw new ChambreNotFoundException("Could not find room with id :" +id);
        }
        chambreRepository.deleteById(id);
    }

}
