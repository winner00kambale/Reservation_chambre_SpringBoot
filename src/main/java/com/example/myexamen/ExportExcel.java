package com.example.myexamen;

import com.example.myexamen.VuePackage.vue;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExportExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<vue> vueList;

    public ExportExcel(List<vue> vueList){
        this.vueList=vueList;
        workbook=new XSSFWorkbook();
    }
    private void writeHeaderLine(){
        sheet=workbook.createSheet("Users");
        Row row=sheet.createRow(0);
        CellStyle style=workbook.createCellStyle();
        XSSFFont font=workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row,0,"Id",style);
        createCell(row,1,"nom client",style);
        createCell(row,2,"nom chambre",style);
        createCell(row,3,"Prix/Jour",style);
        createCell(row,4,"date debut",style);
        createCell(row,5,"date fin",style);
        createCell(row,6,"nombre jour",style);
        createCell(row,7,"montant total",style);
    }
    private void createCell(Row row,int columnCount,Object value,CellStyle style){
        sheet.autoSizeColumn(columnCount);
        Cell cell=row.createCell(columnCount);
        if(value instanceof Integer){
            cell.setCellValue((Integer) value);
        }
        else if(value instanceof Boolean){
            cell.setCellValue((String) value);
        }
        else if(value instanceof Long){
            cell.setCellValue((String) value);
        }
        else{
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    private void writeDataLines()
    {
        int rowCount=1;
        CellStyle style=workbook.createCellStyle();
        XSSFFont font=workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for(vue vue: vueList){
            Row row=sheet.createRow(rowCount++);
            int columnCount=0;
            createCell(row,columnCount++,""+vue.getId(),style);
            createCell(row,columnCount++,""+vue.getClient(),style);
            createCell(row,columnCount++,""+vue.getChambre(),style);
            createCell(row,columnCount++,""+vue.getPrix() +"USD" ,style);
            createCell(row,columnCount++,""+vue.getDebut(),style);
            createCell(row,columnCount++,""+vue.getFin(),style);
            createCell(row,columnCount++,""+vue.getNombre_jours(),style);
            createCell(row,columnCount++,""+vue.getMontant()+ " USD" ,style);
        }

    }
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
