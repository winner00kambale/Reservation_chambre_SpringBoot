package com.example.myexamen.reservations;

import com.example.myexamen.chambres.ChambreNotFoundException;
import com.example.myexamen.chambres.ChambreServiceImpl;
import com.example.myexamen.chambres.Chambres;
import com.example.myexamen.clients.ClientService;
import com.example.myexamen.clients.Clients;
import com.example.myexamen.mainPachage.maincontroller;
import com.example.myexamen.VuePackage.vue;
import com.example.myexamen.VuePackage.vueRepository;
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
public class reservationController {
      @Autowired private ChambreServiceImpl service;
      @Autowired ReservationService serviceReservation;
      @Autowired private ClientService cliservi;
      @Autowired private ReservationRepository repositoryReservation;
    @Autowired private vueRepository vueRepos;
    @Autowired private maincontroller maincontroller;

    @GetMapping("/showReservation")
    public String showreservation(Model model, HttpSession session){
    List<Chambres> chambreLibre = service.listAllByEtat();
    List<Chambres> chambreOccupee = service.listByEtat();
    model.addAttribute("chambreLibre",chambreLibre);
    model.addAttribute("chambreOccupee",chambreOccupee);
        return maincontroller.SecuriteConnexion(model,session,"Reservations");
//        return "Reservations";
    }
    @GetMapping("/reservation/add")
    public String addReservation(Model model,HttpSession session){
        return maincontroller.SecuriteConnexion(model,session,"ReservationSave");
//        return "ReservationSave";
    }
    @GetMapping("/reservation/add/{id}")
    public String showEditReservation(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Chambres chambres = service.get(id);
            model.addAttribute("chambres",chambres);
            List<Clients> nomCli = cliservi.listAll();
            model.addAttribute("nomCli", nomCli);
            return "ReservationSave";
        } catch (ChambreNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/showReservation";
        }
    }
    @PostMapping("/saveReservation")
    public String insertionReservation(@RequestParam("client") int client,@RequestParam("name") String name,
                                       @RequestParam("prix") float prix,@RequestParam("devise") String devise,
                                       @RequestParam("debut") String debut,@RequestParam("fin") String fin){
        int idc = repositoryReservation.GetName(name);
        serviceReservation.insererReservation(client,idc,prix,devise,debut,fin);
        repositoryReservation.UpdateChambre(idc);
        return "redirect:/showReservation";
    }
    @GetMapping("/chambre/{id}")
    public String showChabreDetail(@PathVariable("id") int id, Model model,RedirectAttributes ra,HttpSession session){
        List<vue> liste = vueRepos.test(id);
            model.addAttribute("liste",liste);
        return maincontroller.SecuriteConnexion(model,session,"DetailReservation");
//            return "DetailReservation";
    }
}
