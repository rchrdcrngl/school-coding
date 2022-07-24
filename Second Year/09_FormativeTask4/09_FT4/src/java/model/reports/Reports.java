package model.reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Arrays;
import model.account.Account;

public class Reports{
    Document document;

    public Document getDocument() {
        return document;
    }
    
    public Reports() throws DocumentException{
        document = new Document();
    }
    
    public void createAdminReport(String[][] table) throws DocumentException{
        document.open();
        document.add(new Paragraph("Welcome"));
        document.add(new Paragraph("User List"));
        document.add(userlist(table));
        document.close();
    }
    
    public void createGuestReport(Account acc) throws DocumentException{
        document.open();
        document.add(new Paragraph("Welcome"));
        document.add(new Paragraph("User Info"));
        document.add(new Paragraph("Name: " + acc.getFullName()));
        document.add(new Paragraph("Username: " + acc.getUsername()));
        document.add(new Paragraph("User Type: " + acc.getType()));
        document.close();
    }
    
    public PdfPTable userlist(String[][] table){
        PdfPTable list = new PdfPTable(table[0].length);
        if(table==null){
            table = new String[][]{
            {"Username","User Type"},
            {"null", "null"}
            };
        }
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table[i].length; j++){
                list.addCell(table[i][j]);
            }
        }
        return list;
    }
}