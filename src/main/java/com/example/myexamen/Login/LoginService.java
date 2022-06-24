package com.example.myexamen.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
@Service
public class LoginService {
    @Autowired private LoginRepository loginRepository;
    List<Login> lisAll(){
        return (List<Login>) loginRepository.findAll();
    }
}
