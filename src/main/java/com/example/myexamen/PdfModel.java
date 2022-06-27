package com.example.myexamen;

import com.example.myexamen.VuePackage.vueRepository;
import com.example.myexamen.chambres.ChambreServiceImpl;
import com.example.myexamen.chambres.Chambres;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class PdfModel {
    @Autowired private ChambreServiceImpl chambreService;
    @Autowired private vueRepository vueRepository;
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.white);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("ID",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Nom chambre",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Designation",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Prix/Jour",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Devise",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("etat chambre",font));
        table.addCell(cell);
    }
    private void writeTableData(PdfPTable table){
        List<Chambres> listchambre = chambreService.listAll();

        for (Chambres chambre : listchambre){
            table.addCell(""+chambre.getId());
            table.addCell(chambre.getName());
            table.addCell(chambre.getDesignation());
            table.addCell(""+chambre.getPrix());
            table.addCell(chambre.getDevise());
            table.addCell(chambre.getEtat());
        }
    }
    private void AttestationReservation(PdfPTable table){
    }
    public void exportAttestation(HttpServletResponse response,Integer id) throws DocumentException, IOException{
        Document document=new Document(PageSize.A6);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
        Image image = Image.getInstance("C:\\images\\attestation1.gif");
        image.scaleAbsolute(250,60);
        image.setAlignment(Paragraph.ALIGN_CENTER);
        Font fontl=FontFactory.getFont(FontFactory.TIMES);
        fontl.setSize(10);
        fontl.setColor(Color.black);
        Paragraph pl=new Paragraph("___________________________________________",fontl);
        pl.setAlignment(Paragraph.ALIGN_CENTER);
        Font font=FontFactory.getFont(FontFactory.TIMES_ITALIC);
        font.setSize(12);
        font.setColor(Color.BLACK);
        Paragraph p=new Paragraph(""+vueRepository.AllbyId(id) +" USD",font);
        p.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(image);
        document.add(pl);
        document.add(p);
        document.add(pl);
        Font font4=FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font4.setSize(12);
        font4.setColor(Color.black);
        Paragraph p4=new Paragraph("Agent Hotel Linda Goma",font4);
        p4.setAlignment(Paragraph.ALIGN_RIGHT);
        PdfPTable table=new PdfPTable(1);
        table.setWidthPercentage(95f);
        table.setWidths(new float[]{6.2f});
        table.setSpacingBefore(10);
        AttestationReservation(table);
        document.add(table);
        document.add(p4);
        document.close();
        document.open();
    }
    public void  export(HttpServletResponse response) throws DocumentException, IOException{
        Document document=new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
        Font font4=FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font4.setSize(12);
        font4.setColor(Color.black);
        Paragraph p4=new Paragraph("Agent Hotel Linda Goma",font4);
        p4.setAlignment(Paragraph.ALIGN_RIGHT);
        Image image = Image.getInstance("C:\\images\\linda.gif");
        image.scaleAbsolute(527,60);
        image.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(image);
        PdfPTable table=new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.0f,1.7f,6.0f,2.0f,1.5f,2.0f});
        table.setSpacingBefore(10);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.add(p4);
        document.close();
        document.open();
    }
}
