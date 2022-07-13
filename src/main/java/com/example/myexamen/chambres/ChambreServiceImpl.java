package com.example.myexamen.chambres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
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
    public void insererchambre(String name, String designation, float prix, String devise,MultipartFile file){Chambres c = new Chambres();
       String filename= StringUtils.cleanPath(file.getOriginalFilename());
       if(filename.contains("..")){
           System.out.println("null");
       }
       try{
           c.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
       }catch (Exception e){
           throw new RuntimeException(e);
       }
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
