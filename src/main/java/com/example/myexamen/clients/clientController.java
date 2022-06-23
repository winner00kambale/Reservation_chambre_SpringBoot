package com.example.myexamen.clients;

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
public class clientController {
    @Autowired private ClientService service;
    @Autowired private maincontroller maincontroller;

    @GetMapping("/CliensController")
    public String showClient(Model model, HttpSession session){
        List<Clients> Listclients = service.listAll();
        model.addAttribute("Listclients", Listclients);
        return maincontroller.SecuriteConnexion(model,session,"clients");
//        return "clients";
    }
    @GetMapping("showSavecli")
    public  String showSavecli(Model model,HttpSession session){
        return maincontroller.SecuriteConnexion(model,session,"clientSave");
//        return "clientSave";
    }
    @PostMapping("/addClient")
    public String showSave(@RequestParam("nom") String nom, @RequestParam("postnom") String postnom,
                           @RequestParam("prenom") String prenom, @RequestParam("genre") String genre,
                           @RequestParam("nationalite") String nationalite, @RequestParam("profession") String profession,
                           @RequestParam("etatcivil") String etatcivil, @RequestParam("mail") String mail,
                           @RequestParam("adresse") String adresse, @RequestParam("telephone") String telephone, RedirectAttributes ra){
        service.insererclient(nom,postnom,prenom,genre,nationalite,profession,etatcivil,mail,adresse,telephone);
        ra.addFlashAttribute("message","saved succesfuly");
        return "redirect:/CliensController";
    }

    @GetMapping("client/edit/{id}")
    public String showUpdate(@PathVariable("id") Integer id,Model model, RedirectAttributes ra ){
        try{
            Clients client = service.get(id);
            model.addAttribute("pageTitle","Edit client(ID:"+id+" )");
            model.addAttribute("client",client);
            return "updateClient";
        }catch (RuntimeException e){
            ra.addFlashAttribute("message","The user has been update successfull");
            return "redirect:/CliensController";
        }
        }
    @PostMapping("/client/update")
    public String updateClient(Clients cli){
        service.save(cli);
        return "redirect:/CliensController";

    }
}
