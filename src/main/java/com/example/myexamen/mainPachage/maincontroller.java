package com.example.myexamen.mainPachage;

import com.example.myexamen.ExportExcel;
import com.example.myexamen.VuePackage.vue;
import com.example.myexamen.VuePackage.vueRepository;
import com.example.myexamen.chambres.ChambreRepository;
import com.example.myexamen.clients.ClientsRepository;
import com.example.myexamen.mailController.MailService;
import com.example.myexamen.reservations.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class maincontroller {
    @Autowired
    MailService mailService;
    @Autowired private
    vueRepository vueRepository;
    @Autowired private
    ClientsRepository clientsRepository;
    @Autowired private ChambreRepository chambreRepository;
    @Autowired private ReservationRepository reservationRepository;
    @GetMapping("/")
    public String showHome(Model model, HttpSession session)
    {   long countcli = clientsRepository.count();
        long countchambre = chambreRepository.count();
        long countreservation = reservationRepository.count();
        int countBydate = reservationRepository.allByDate();
        model.addAttribute("countreservation",countreservation);
        model.addAttribute("countchambre",countchambre);
        model.addAttribute("countcli",countcli);
        model.addAttribute("countBydate",countBydate);
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
    @GetMapping("/reservationExcel")
    public void excelReservation(HttpServletResponse response)throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentateTime=dateFormatter.format(new Date(0));
        String headerkey="Content-Disposition";
        String headerValue="attachment;filename=rapport de reservation"+currentateTime+".xlsx";
        response.setHeader(headerkey,headerValue);
        List<vue> vues=vueRepository.All();
        ExportExcel exportExcel1 = new ExportExcel(vues);
        exportExcel1.export(response);

    }
}
