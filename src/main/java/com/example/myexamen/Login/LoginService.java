package com.example.myexamen.Login;

import com.example.myexamen.chambres.ChambreNotFoundException;
import com.example.myexamen.chambres.Chambres;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired private LoginRepository loginRepository;
    List<Login> lisAll(){
        return (List<Login>) loginRepository.findAll();
    }
    public Login get(Integer id) throws ChambreNotFoundException {
        Optional<Login> result = loginRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ChambreNotFoundException("Could not find room with id :" +id);
    }
}
