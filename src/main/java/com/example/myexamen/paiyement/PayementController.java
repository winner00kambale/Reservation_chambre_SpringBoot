package com.example.myexamen.paiyement;

import com.example.myexamen.PdfModel;
import com.example.myexamen.VuePackage.vue;
import com.example.myexamen.VuePackage.vuePayement;
import com.example.myexamen.VuePackage.vuePayementRepository;
import com.example.myexamen.VuePackage.vueRepository;
import com.example.myexamen.mainPachage.maincontroller;
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
public class PayementController {
    @Autowired private vuePayementRepository payementRepository;
    @Autowired private maincontroller maincontroller;
    @Autowired private vueRepository vueRepository;
    @Autowired private PayementService service;
    @Autowired private PdfModel pdfModel;
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
            ra.addFlashAttribute("message"," Payement effectue avec succes ");
            return "redirect:/payementshow";
        }catch (Exception e){
            ra.addFlashAttribute("message"," ce payement existe deja ");
            return "redirect:/payementshow";
        }
    }
    @GetMapping("/ListePayement")
    public String getListPayement(Model model,HttpSession session){
        List<vuePayement> ListPayement = payementRepository.allReservationPayee();
        model.addAttribute("ListPayement",ListPayement);
        return maincontroller.SecuriteConnexion(model,session,"PayementShow");
    }
    @PostMapping("/rapportPayement/pdf")
    public void exportRapport(HttpServletResponse response, @RequestParam("date") String date) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentateTime=dateFormatter.format(new Date(0));
        String headerkey="Content-Disposition";
        String headerValue="attachment;filename=RAPPORT PAYEMENT"+currentateTime+".pdf";
        response.setHeader(headerkey,headerValue);
        pdfModel.ExportRapportPayement(response,date);
    }
}

