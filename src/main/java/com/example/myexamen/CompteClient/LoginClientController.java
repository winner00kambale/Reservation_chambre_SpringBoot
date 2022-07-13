package com.example.myexamen.CompteClient;

import com.example.myexamen.chambres.ChambreNotFoundException;
import com.example.myexamen.chambres.ChambreServiceImpl;
import com.example.myexamen.chambres.Chambres;
import com.example.myexamen.clients.ClientService;
import com.example.myexamen.clients.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LoginClientController {
    @Autowired private ChambreServiceImpl service;
    @Autowired private ClientService cliservi;
    @GetMapping("/LoginClient")
    public String showLoginClient(){
        return "LoginClient";
    }
    @GetMapping("/CompteClient")
    public String showCompteClient(){
        return "CompteClient";
    }
    @GetMapping("/home")
    public String home(){
        return "Home";
    }
    @GetMapping("/reservationClient")
    public String showReservationClient(Model model){
        List<Chambres> chambreLibres = service.listAllByEtat();
        model.addAttribute("chambreLibres",chambreLibres);
        return "ReservationClient";
    }
    @GetMapping("/reservationClient/add/{id}")
    public String showEditReservation(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Chambres chambres = service.get(id);
            model.addAttribute("chambresreservation",chambres);
            List<Clients> nomCli = cliservi.listAll();
            model.addAttribute("nomCli", nomCli);
            return "ReservationClientSave";
        } catch (ChambreNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/ReservationClient";
        }
    }
}
