package com.example.myexamen.chambres;

import com.example.myexamen.PdfModel;
import com.example.myexamen.mainPachage.maincontroller;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ChambreController {

    @Autowired private ChambreServiceImpl service;
    @Autowired private maincontroller maincontroller;
    @Autowired private PdfModel pdfModel;
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
    }
    @PostMapping("/room/save")
    public String saveRoom(@RequestParam("image")MultipartFile file, @RequestParam("name") String name, @RequestParam("designation") String designation,
                           @RequestParam("prix") float prix, @RequestParam("devise") String devise, RedirectAttributes ra){
        try{
            service.insererchambre(name,designation,prix,devise,file);
            ra.addFlashAttribute("message"," saved succesfuly ");
            return "redirect:/Showchambre";
        }catch (Exception e){
            ra.addFlashAttribute("message"," cette chambre existe deja ");
            return "redirect:/chambre/new";
        }

    }
    @PostMapping("/room/update")
    public String updateRoom(Chambres chambre, RedirectAttributes ra){
        service.save(chambre);
        ra.addFlashAttribute("message"," Room updated successfuly ");
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
    @GetMapping("/chambre/pdf")
    public void exporttoPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentateTime=dateFormatter.format(new Date(0));
        String headerkey="Content-Disposition";
        String headerValue="attachment;filename=LISTE DE CHAMBRE"+currentateTime+".pdf";
        response.setHeader(headerkey,headerValue);
        pdfModel.export(response);
    }

}
