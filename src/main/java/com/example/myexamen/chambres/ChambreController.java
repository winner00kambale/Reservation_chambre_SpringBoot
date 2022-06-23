package com.example.myexamen.chambres;

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
public class ChambreController {

    @Autowired private ChambreServiceImpl service;
    @Autowired private maincontroller maincontroller;
    @GetMapping("Showchambre")
    public String ShowChambre(Model model, HttpSession session){
        List<Chambres> chambrelist=service.listAll();
        model.addAttribute("chambrelist",chambrelist);
        return maincontroller.SecuriteConnexion(model,session,"chambres");
    }
    @GetMapping("/chambre/new")
    public String showNewRoom(Model model,HttpSession session){
        model.addAttribute("chambre", new Chambres());
        return maincontroller.SecuriteConnexion(model,session,"ChambreSave");
//        return "ChambreSave";
    }
    @PostMapping("/room/save")
    public String saveRoom(@RequestParam("name") String name, @RequestParam("designation") String designation,
                           @RequestParam("prix") float prix, @RequestParam("devise") String devise, RedirectAttributes ra){
        service.insererchambre(name,designation,prix,devise);
        ra.addFlashAttribute("message"," saved succesfuly ");
        return "redirect:/Showchambre";
    }
    @PostMapping("/room/update")
    public String updateRoom(Chambres chambre, RedirectAttributes ra){
        service.save(chambre);
        ra.addFlashAttribute("message"," Room updated succesfuly ");
        return "redirect:/Showchambre";
    }
    @GetMapping("/chambre/edit/{id}")
    public String showChabreForm(@PathVariable("id") Integer id, Model model,RedirectAttributes ra){
        try {
            Chambres chambre = service.get(id);
            model.addAttribute("chambre",chambre);
            return "updateChambre";
        } catch (ChambreNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/Showchambre";
        }
    }
    @GetMapping("/chambre/delete/{id}")
    public String deleteChabreForm(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message","la chambre numero : " + id + " est supprimee avec succes");
        } catch (ChambreNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/Showchambre";
    }

}
