package com.example.myexamen.paiyement;

import com.example.myexamen.VuePackage.vue;
import com.example.myexamen.VuePackage.vuePayement;
import com.example.myexamen.VuePackage.vuePayementRepository;
import com.example.myexamen.VuePackage.vueRepository;
import com.example.myexamen.mainPachage.maincontroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PayementController {
    @Autowired private vuePayementRepository payementRepository;
    @Autowired private maincontroller maincontroller;
    @Autowired private vueRepository vueRepository;
    @Autowired private PayementService service;
    @GetMapping("/payementshow")
    public String getPayement(Model model, HttpSession session){
        List<vuePayement> vuesPay = payementRepository.allReservation();
        List<vuePayement> vueListPay = payementRepository.allReservationPayee();
        model.addAttribute("vuesPay",vuesPay);
        model.addAttribute("vueListPay",vueListPay);
        return maincontroller.SecuriteConnexion(model,session,"Payement");
    }
    @GetMapping("/payement/{id}")
    public String showPayement(@PathVariable("id") int id, Model model,HttpSession session){
        List<vue> listePaye = vueRepository.AllReserById(id);
        model.addAttribute("liste",listePaye);
        return maincontroller.SecuriteConnexion(model,session,"PayementSave");
    }
    @PostMapping("/payement/save")
    public String payementRoom(@RequestParam("reservation") int resrvation, @RequestParam("montant") float montant,
                           @RequestParam("jours") int jours, @RequestParam("devise") String devise, RedirectAttributes ra){
        try{
            service.SavePayement(resrvation,montant,jours,devise);
            ra.addFlashAttribute("message"," saved succesfuly ");
            return "redirect:/payementshow";
        }catch (Exception e){
            ra.addFlashAttribute("message"," cette chambre existe deja ");
            return "redirect:/payementshow";
        }

    }
}

