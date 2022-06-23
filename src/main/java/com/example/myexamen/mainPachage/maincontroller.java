package com.example.myexamen.mainPachage;

import com.example.myexamen.mailController.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class maincontroller {
    @Autowired
    MailService mailService;
    @GetMapping("/")
    public String showHome(Model model, HttpSession session){
        return ""+SecuriteConnexion(model,session,"index");
    }
    public String SecuriteConnexion(Model model,HttpSession session,String root){
        List<String> messages=(List<String>) session.getAttribute("MY_SESSION_MESSAGE");
        if (messages==null){
            return "redirect:/login";
        }else {
            return ""+root;
        }
    }
    @GetMapping("/login")
    public String showLoginPage(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }
    @GetMapping("/sendMail")
    public void sendMail(){
        mailService.sendEmail("kambalekarah@icloud.com","salutation","salut bro");
    }
}
