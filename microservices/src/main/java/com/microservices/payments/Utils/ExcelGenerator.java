package com.microservices.payments.Utils;

import com.microservices.payments.Payment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class ExcelGenerator {
    public static XSSFWorkbook generateExcel(List<Payment> payments){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Payments");
        XSSFRow rowhead = sheet.createRow((short)0);
        rowhead.createCell(0).setCellValue("ID");
        rowhead.createCell(1).setCellValue("Vrijeme ocitanja");
        rowhead.createCell(2).setCellValue("Uredaj");
        rowhead.createCell(3).setCellValue("Naplatna tocka");
        rowhead.createCell(4).setCellValue("Kategorija");
        rowhead.createCell(5).setCellValue("ID ENC");
        rowhead.createCell(6).setCellValue("Registracijska oznaka");
        short rownum = 1;
        for (Payment p: payments) {
            XSSFRow row = sheet.createRow(rownum);
            row.createCell(0).setCellValue(p.getId());
            row.createCell(1).setCellValue(p.getVrijemeOcitanja().toString());
            row.createCell(2).setCellValue(p.getUredaj());
            row.createCell(3).setCellValue(p.getNaplatnaTocka());
            row.createCell(4).setCellValue(p.getKategorija());
            row.createCell(5).setCellValue(p.getIdENC());
            row.createCell(6).setCellValue(p.getRegistracijskaOznaka());
            rownum++;
        }
        return workbook;
    }
}
