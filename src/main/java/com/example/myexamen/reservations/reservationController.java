package com.example.myexamen.reservations;

import com.example.myexamen.PdfModel;
import com.example.myexamen.chambres.ChambreNotFoundException;
import com.example.myexamen.chambres.ChambreServiceImpl;
import com.example.myexamen.chambres.Chambres;
import com.example.myexamen.clients.ClientService;
import com.example.myexamen.clients.Clients;
import com.example.myexamen.mailController.MailService;
import com.example.myexamen.mainPachage.maincontroller;
import com.example.myexamen.VuePackage.vue;
import com.example.myexamen.VuePackage.vueRepository;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class reservationController {
      @Autowired private ChambreServiceImpl service;
      @Autowired ReservationService serviceReservation;
      @Autowired private ClientService cliservi;
      @Autowired private ReservationRepository repositoryReservation;
      @Autowired private vueRepository vueRepos;
      @Autowired private maincontroller maincontroller;
      @Autowired private vueRepository getVueRepos;
      @Autowired private MailService mailService;
      @Autowired private PdfModel pdfModel;

      @GetMapping("/rapportReservation")
      public String rapportReservat(Model model, HttpSession session){
          List<vue> rappot = getVueRepos.All();
          model.addAttribute("rappot",rappot);
          return maincontroller.SecuriteConnexion(model,session,"ListReservation");
      }
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
                                       @RequestParam("debut") String debut,@RequestParam("fin") String fin,RedirectAttributes ra){
        int idc = repositoryReservation.GetName(name);
        String mail = repositoryReservation.getMailClient(client);
        String prenomclient = repositoryReservation.getprenomClient(client);
        serviceReservation.insererReservation(client,idc,prix,devise,debut,fin);
        repositoryReservation.UpdateChambre(idc);
        mailService.sendEmail(mail,"Confirmation de reservation",
                "SALUT Mr/Mm : " + prenomclient + " VOUS AVEZ RESERVE LA CHAMBRE " + name + " NUMERO " + idc + "," +
                        " DU " + debut + " AU " + fin + " POUR LE PRIX DE " + prix + " USD PAR JOUR. MERCI !!!. DG Hotel Linda Goma");
        ra.addFlashAttribute("message","saved succesfuly");
        return "redirect:/showReservation";
    }
    @GetMapping("/chambre/{id}")
    public String showChabreDetail(@PathVariable("id") int id, Model model,RedirectAttributes ra,HttpSession session){
        List<vue> liste = vueRepos.test(id);
            model.addAttribute("liste",liste);
        return maincontroller.SecuriteConnexion(model,session,"DetailReservation");
//            return "DetailReservation";
    }
    @GetMapping("/attest/pdf/{id}")
    public void exportAttest(HttpServletResponse response,@PathVariable("id") Integer id) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentateTime=dateFormatter.format(new Date(0));
        String headerkey="Content-Disposition";
        String headerValue="attachment;filename=ATTESTATION RESERVATION"+currentateTime+".pdf";
        response.setHeader(headerkey,headerValue);
        pdfModel.exportAttestation(response,id);
    }
}
