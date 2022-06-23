package com.example.myexamen.Login;

import com.example.myexamen.reservations.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired private LoginRepository loginRepo;
    @Autowired ReservationService reserv;
    @PostMapping("/check_login")
    public String testerLogin(@RequestParam("mail") String email, @RequestParam("password") String password, Model model, RedirectAttributes ra,HttpServletRequest request,HttpSession session){
        Optional<Login> logins = loginRepo.testmail(email,password);
        if (logins.isPresent()){
            List<String> msgs=(List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGE");
            if (msgs==null){
                msgs=new ArrayList<>();
                request.getSession().setAttribute("MY_SESSION_MESSAGE",msgs);
            }
            msgs.add("welcome");
            request.getSession().setAttribute("MY_SESSION_MESSAGE",msgs);
            reserv.addTest();
            return "redirect:/";
        }else {
            ra.addFlashAttribute("error","Verifier votre UserName et votre mot Password svp !!!");
            return "redirect:/login";
        }
    }
}
