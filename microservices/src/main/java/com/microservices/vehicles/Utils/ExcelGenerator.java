package com.microservices.vehicles.Utils;

import com.microservices.vehicles.Vehicle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class ExcelGenerator {

    public static XSSFWorkbook generateExcel(List<Vehicle> vehicles){

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Vehicles");
        XSSFRow rowhead = sheet.createRow((short)0);
        rowhead.createCell(0).setCellValue("ID");
        rowhead.createCell(1).setCellValue("Nacin naplate");
        rowhead.createCell(2).setCellValue("Boja");
        rowhead.createCell(3).setCellValue("Broj osovina");
        rowhead.createCell(4).setCellValue("VIN");
        rowhead.createCell(5).setCellValue("ID ENC");
        rowhead.createCell(6).setCellValue("Registracijska oznaka");
        rowhead.createCell(7).setCellValue("Eko razred");
        rowhead.createCell(8).setCellValue("Kategorija");
        rowhead.createCell(9).setCellValue("Drzava registracije");
        rowhead.createCell(10).setCellValue("Oznaka autoceste");
        rowhead.createCell(11).setCellValue("Pocetna dionica oznaka");
        rowhead.createCell(12).setCellValue("Pocetna dionica ID");
        rowhead.createCell(13).setCellValue("Zavrsna dionica oznaka");
        rowhead.createCell(14).setCellValue("Zavrsna dionica ID");
        rowhead.createCell(15).setCellValue("Prosjecna brzina");
        rowhead.createCell(16).setCellValue("Vrijeme");
        short rownum = 1;
        for (Vehicle v: vehicles
             ) {
            XSSFRow row = sheet.createRow(rownum);
            row.createCell(0).setCellValue(v.getId());
            row.createCell(1).setCellValue(v.getNacinNaplate());
            row.createCell(2).setCellValue(v.getBoja());
            row.createCell(3).setCellValue(v.getBrojOsovina());
            row.createCell(4).setCellValue(v.getVIN());
            row.createCell(5).setCellValue(v.getIdENC());
            row.createCell(6).setCellValue(v.getRegistracijskaOznaka());
            row.createCell(7).setCellValue(v.getEkoRazred());
            row.createCell(8).setCellValue(v.getKategorija());
            row.createCell(9).setCellValue(v.getDrzavaRegistracije());
            row.createCell(10).setCellValue(v.getOznakaAutoceste());
            row.createCell(11).setCellValue(v.getPocetnaDionicaOznaka());
            row.createCell(12).setCellValue(v.getPocetnaDionicaId());
            row.createCell(13).setCellValue(v.getZavrsnaDionicaOznaka());
            row.createCell(14).setCellValue(v.getZavrsnaDionicaId());
            row.createCell(15).setCellValue(v.getProsjecnaBrzina());
            row.createCell(16).setCellValue(v.getVrijeme());
            rownum++;
        }

        return workbook;
    }
}
