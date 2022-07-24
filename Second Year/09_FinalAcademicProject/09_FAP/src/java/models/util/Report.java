package models.util;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.TextAlignment;
import javax.servlet.ServletOutputStream;
import models.entity.Order;
import models.entity.Product;
import models.entity.User;

public class Report {

    String author;
    Document document;
    static DecimalFormat df = new DecimalFormat();
    PdfWriter pdfWriter;
    Font[] fonts = {
        //new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, new BaseColor(255,255,255)),
        new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD),
        new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL),
        new Font(Font.FontFamily.COURIER, 12, Font.NORMAL),};

    public Document getDocument() {
        return document;
    }

    public Report(ServletOutputStream out, String author) throws DocumentException {
        document = new Document();
        df.setMaximumFractionDigits(2);
        //Set Author Attribute
        this.author = author;
        //Set Output Stream
        pdfWriter = PdfWriter.getInstance(document, out);
        //Set Page
        HeaderAndFooterPdfPageEventHelper headerAndFooter = new HeaderAndFooterPdfPageEventHelper();
        pdfWriter.setPageEvent(headerAndFooter);
    }

    public Report(FileOutputStream out, String author) throws DocumentException {
        document = new Document();
        df.setMaximumFractionDigits(2);
        //Set Author Attribute
        this.author = author;
        //Set Output Stream
        pdfWriter = PdfWriter.getInstance(document, out);
        //Set Page
        HeaderAndFooterPdfPageEventHelper headerAndFooter = new HeaderAndFooterPdfPageEventHelper();
        pdfWriter.setPageEvent(headerAndFooter);
    }

    public PdfPTable arrayToTable(String[][] table) {
        PdfPTable list = new PdfPTable(table[0].length);
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                list.addCell(table[i][j]);
            }
        }
        return list;
    }

    public void header() throws DocumentException {
        Image image;
        try {
            image = Image.getInstance("web/resources/site/header.png");
            image.scaleAbsolute(300,45);
            document.add(image);
        } catch (BadElementException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        DottedLineSeparator separator = new DottedLineSeparator();
        //separator.setPercentage(59500f / 523f);
        separator.setOffset(-2);
        separator.setGap(2f);
        Chunk linebreak = new Chunk();
        document.add(separator);
        document.add(linebreak);
    }

    public void generateOrderInvoice(Order order) throws DocumentException {
        Rectangle r = new Rectangle(PageSize.A5);
        document.setPageSize(r);
        pdfWriter.setBoxSize("rectangle", r);
        document.addTitle("Order Invoice");
        document.open();
        document.addAuthor(author);
        
        header();
        
        Paragraph title;
        title = new Paragraph("Order Invoice", fonts[0]);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10);
        document.add(title);

        //Order Info
        PdfPTable info = new PdfPTable(2);
        PdfPCell c = new PdfPCell();
        c.setBorder(0);
        c.setPhrase(new Phrase("Order No: "+order.getId(),fonts[2]));
        info.addCell(c);
        c.setBorder(0);
        c.setPhrase(new Phrase("Order Date: "+order.getCreatedAt(),fonts[2]));
        info.addCell(c);
        c.setBorder(0);
        c.setPhrase(new Phrase("Name: "+order.getUser().getFirstName()+" "+order.getUser().getLastName(),fonts[2]));
        c.setColspan(2);
        info.addCell(c);
        c.setBorder(0);
        c.setPhrase(new Phrase("Address: "+order.getUser().getAddress()+", "+order.getUser().getCity()+", "+order.getUser().getCountry()+" "+order.getUser().getZip_code(),fonts[2]));
        c.setColspan(2);
        info.addCell(c);
        c.setBorder(0);
        c.setPhrase(new Phrase("Courier Name: "+order.getCourier().getCourierName(),fonts[2]));
        info.addCell(c);
        c.setBorder(0);
        c.setPhrase(new Phrase("Tracking No.: "+order.getTrackingNo(),fonts[2]));
        info.addCell(c);
        c.setBorder(0);
        c.setPhrase(new Phrase("Generated by: "+author,fonts[2]));
        c.setColspan(2);
        info.addCell(c);
        info.setWidthPercentage(100);
        info.setSpacingAfter(20);
        document.add(info);
        
        title = new Paragraph("Order Items", fonts[1]);
        title.setSpacingAfter(10);
        document.add(title);
        
        //Table
        PdfPTable list = new PdfPTable(3);
        list.setWidthPercentage(100);
        list.addCell(new Phrase("Product Name", fonts[0]));
        list.addCell(new Phrase("Quantity", fonts[0]));
        list.addCell(new Phrase("Price", fonts[0]));
        for (Product p : order.getProducts()) {
            list.addCell(new Phrase(p.getTitle(), fonts[1]));
            list.addCell(new Phrase(Integer.toString(p.getStock()), fonts[1]));
            list.addCell(new Phrase("PHP. "+df.format(p.getPrice()), fonts[1]));
        }
        list.setSpacingAfter(10);
        document.add(list);
        
        document.add(new Chunk());
        Paragraph p2;
        p2 = new Paragraph("SUBTOTAL: "+order.getSubtotal(),fonts[0]);
        p2.setAlignment(Element.ALIGN_RIGHT);
        document.add(p2);
        p2 = new Paragraph("SHIPPING FEE: "+order.getCourier().getPrice(),fonts[0]);
        p2.setAlignment(Element.ALIGN_RIGHT);
        document.add(p2);
        p2 = new Paragraph("TOTAL: "+order.getTotal(),fonts[0]);
        p2.setAlignment(Element.ALIGN_RIGHT);
        document.add(p2);
        //document.close();
    }

    public void generateOrderSlip(Order order) throws DocumentException {
        Rectangle r = new Rectangle(PageSize.A5);
        document.setPageSize(r);
        pdfWriter.setBoxSize("rectangle", r);
        document.addTitle("Order Slip");
        document.open();
        document.addAuthor(author);
        
        header();
        
        Paragraph title;
        title = new Paragraph("Order Slip", fonts[0]);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10);
        document.add(title);

        //Order Info
        PdfPTable info = new PdfPTable(2);
        PdfPCell c = new PdfPCell();
        c.setBorder(0);
        c.setPhrase(new Phrase("Order No: "+order.getId(),fonts[2]));
        info.addCell(c);
        c.setBorder(0);
        c.setPhrase(new Phrase("Order Date: "+order.getCreatedAt(),fonts[2]));
        info.addCell(c);
        c.setBorder(0);
        c.setPhrase(new Phrase("Name: "+order.getUser().getFirstName()+" "+order.getUser().getLastName(),fonts[2]));
        c.setColspan(2);
        info.addCell(c);
        c.setBorder(0);
        c.setPhrase(new Phrase("Address: "+order.getUser().getAddress()+", "+order.getUser().getCity()+", "+order.getUser().getCountry()+" "+order.getUser().getZip_code(),fonts[2]));
        c.setColspan(2);
        info.addCell(c);
        c.setBorder(0);
        c.setPhrase(new Phrase("Courier Name: "+order.getCourier().getCourierName(),fonts[2]));
        info.addCell(c);
        c.setBorder(0);
        c.setPhrase(new Phrase("Tracking No.: "+order.getTrackingNo(),fonts[2]));
        info.addCell(c);
        c.setBorder(0);
        c.setPhrase(new Phrase("Generated by: "+author,fonts[2]));
        c.setColspan(2);
        info.addCell(c);
        info.setWidthPercentage(100);
        info.setSpacingAfter(20);
        document.add(info);
        
        title = new Paragraph("Order Items", fonts[1]);
        title.setSpacingAfter(10);
        document.add(title);
        
        //Table
        PdfPTable list = new PdfPTable(4);
        list.setWidthPercentage(100);
        list.addCell(new Phrase("Product Name", fonts[0]));
        list.addCell(new Phrase("SKU", fonts[0]));
        list.addCell(new Phrase("Quantity", fonts[0]));
        list.addCell(new Phrase("Price", fonts[0]));
        for (Product p : order.getProducts()) {
            list.addCell(p.getTitle());
            list.addCell(p.getSku());
            list.addCell(Integer.toString(p.getStock()));
            list.addCell("PHP. "+df.format(p.getPrice()));
        }
        list.setSpacingAfter(20);
        document.add(list);
        
        document.add(new Chunk());
        Paragraph p2;
        p2 = new Paragraph("SUBTOTAL: "+order.getSubtotal(),fonts[0]);
        p2.setAlignment(Element.ALIGN_RIGHT);
        document.add(p2);
        p2 = new Paragraph("SHIPPING FEE: "+order.getCourier().getPrice(),fonts[0]);
        p2.setAlignment(Element.ALIGN_RIGHT);
        document.add(p2);
        p2 = new Paragraph("TOTAL: "+order.getTotal(),fonts[0]);
        p2.setAlignment(Element.ALIGN_RIGHT);
        document.add(p2);
        //document.close();
    }

    public void generateStockList(List<Product> ls) throws DocumentException {
        Rectangle r = new Rectangle(PageSize.LETTER.rotate());
        document.setPageSize(r);
        pdfWriter.setBoxSize("rectangle", r);
        document.addTitle("Stock List");
        document.open();
        document.addAuthor(author);

        header();
        
        Paragraph title = new Paragraph("Stock List", fonts[0]);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10);
        document.add(title);

        //Table
        PdfPTable list = new PdfPTable(6);
        list.setWidthPercentage(100);
        list.addCell(new Phrase("Product ID", fonts[0]));
        list.addCell(new Phrase("Product Name", fonts[0]));
        list.addCell(new Phrase("SKU", fonts[0]));
        list.addCell(new Phrase("Manufacturer", fonts[0]));
        list.addCell(new Phrase("Product Type", fonts[0]));
        list.addCell(new Phrase("Quantity", fonts[0]));
        for (Product p : ls) {
            list.addCell(Integer.toString(p.getId()));
            list.addCell(p.getTitle());
            list.addCell(p.getSku());
            list.addCell(p.getManufacturer());
            list.addCell(p.getType());
            list.addCell(Integer.toString(p.getStock()));
        }
        document.add(list);
        
        Paragraph p2  = new Paragraph("Generated By: "+author);
        p2.setAlignment(Element.ALIGN_RIGHT);
        document.add(p2);
        //document.close();
    }

    public void generateProductList(List<Product> ls) throws DocumentException {
        Rectangle r = new Rectangle(PageSize.LETTER.rotate());
        document.setPageSize(r);
        pdfWriter.setBoxSize("rectangle", r);
        document.addTitle("Product List");
        document.open();
        document.addAuthor(author);
        
        header();
        
        Paragraph title = new Paragraph("Product List", fonts[0]);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10);
        document.add(title);
        
        //Table
        PdfPTable list = new PdfPTable(7);
        list.setWidthPercentage(100);
        list.addCell(new Phrase("Product Name", fonts[0]));
        list.addCell(new Phrase("Price", fonts[0]));
        list.addCell(new Phrase("SKU", fonts[0]));
        list.addCell(new Phrase("Manufacturer", fonts[0]));
        list.addCell(new Phrase("Product Type", fonts[0]));
        list.addCell(new Phrase("Quantity", fonts[0]));
        list.addCell(new Phrase("Active", fonts[0]));
        for (Product p : ls) {
            list.addCell(p.getTitle());
            list.addCell(df.format(p.getPrice()));
            list.addCell(p.getSku());
            list.addCell(p.getManufacturer());
            list.addCell(p.getType());
            list.addCell(Integer.toString(p.getStock()));
            list.addCell(String.valueOf(p.isActive()));
        }
        document.add(list);
        
        Paragraph p2  = new Paragraph("Generated By: "+author);
        p2.setAlignment(Element.ALIGN_RIGHT);
        document.add(p2);
        //document.close();
    }

    public void generateSalesReport(List<Product> ls) throws DocumentException {
        Rectangle r = new Rectangle(PageSize.LETTER.rotate());
        document.setPageSize(r);
        pdfWriter.setBoxSize("rectangle", r);
        document.addTitle("Sales Report");
        document.open();
        
        header();
        
        Paragraph title = new Paragraph("Sales Report", fonts[0]);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10);
        document.add(title);

        //Table
        PdfPTable list = new PdfPTable(6);
        list.setWidthPercentage(100);
        //MIN(PRODUCTID) AS PRODUCTID, MIN(PRODUCTS.TITLE) AS TITLE, MIN(PRODUCTS.SKU) AS SKU, MIN(PRODUCTS.ACTIVE) AS ACTIVE, SUM(ORDER_ITEM.PRICE) AS TOTAL_SALES, SUM(ORDER_ITEM.QUANTITY) AS QTY_SOLD
        list.addCell(new Phrase("Product ID", fonts[0]));
        list.addCell(new Phrase("Product Name", fonts[0]));
        list.addCell(new Phrase("SKU", fonts[0]));
        list.addCell(new Phrase("Active", fonts[0]));
        list.addCell(new Phrase("Quantity Sold", fonts[0]));
        list.addCell(new Phrase("Total Sales", fonts[0]));

        for (Product p : ls) {
            list.addCell(String.valueOf(p.getId()));
            list.addCell(p.getTitle());
            list.addCell(p.getSku());
            list.addCell(String.valueOf(p.isActive()));
            list.addCell(String.valueOf(p.getStock()));
            list.addCell("PHP. "+df.format(p.getPrice()));
        }
        document.add(list);
        
        Paragraph p2  = new Paragraph("Generated By: "+author);
        p2.setAlignment(Element.ALIGN_RIGHT);
        document.add(p2);
        //document.close();
    }

    public void generateGuestList(List<User> ls) throws DocumentException {
        Rectangle r = new Rectangle(PageSize.LETTER.rotate());
        document.setPageSize(r);
        pdfWriter.setBoxSize("rectangle", r);
        document.addTitle("Guest List");
        document.open();
        document.addAuthor(author);
        
        header();
        
        Paragraph title = new Paragraph("Guest List", fonts[0]);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10);
        document.add(title);
        
        
        //Table
        PdfPTable list = new PdfPTable(5);
        list.setWidthPercentage(100);
        //FIRSTNAME, LASTNAME, BIRTHDAY, EMAIL, USERNAME
        list.addCell(new Phrase("First Name", fonts[0]));
        list.addCell(new Phrase("Last Name", fonts[0]));
        list.addCell(new Phrase("Birthday", fonts[0]));
        list.addCell(new Phrase("Email", fonts[0]));
        list.addCell(new Phrase("Username", fonts[0]));
        for (User user : ls) {
            list.addCell(user.getFirstName());
            list.addCell(user.getLastName());
            list.addCell(String.valueOf(user.getBirthday()));
            list.addCell(user.getEmail());
            list.addCell(user.getUsername());
        }
        document.add(list);
        
        Paragraph p2  = new Paragraph("Generated By: "+author);
        p2.setAlignment(Element.ALIGN_RIGHT);
        document.add(p2);
        //document.close();
    }

    public void generateEmployeeList(List<User> ls) throws DocumentException {
        Rectangle r = new Rectangle(PageSize.LETTER.rotate());
        document.setPageSize(r);
        pdfWriter.setBoxSize("rectangle", r);
        document.addTitle("Employee List");
        document.open();
        document.addAuthor(author);
        
        header();
        
        Paragraph title = new Paragraph("Employee List", fonts[0]);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10);
        document.add(title);
        
        //Table
        PdfPTable list = new PdfPTable(8);
        list.setWidthPercentage(100);
        //FIRSTNAME, LASTNAME, BIRTHDAY, MOBILE, EMAIL, ADDRESS, CITY, COUNTRY, USERNAME, USERTYPE
        list.addCell(new Phrase("First Name", fonts[0]));
        list.addCell(new Phrase("Last Name", fonts[0]));
        list.addCell(new Phrase("Birthday", fonts[0]));
        list.addCell(new Phrase("Mobile", fonts[0]));
        list.addCell(new Phrase("Email", fonts[0]));
        list.addCell(new Phrase("Address", fonts[0]));
        list.addCell(new Phrase("Username", fonts[0]));
        list.addCell(new Phrase("User Type", fonts[0]));
        for (User user : ls) {
            list.addCell(user.getFirstName());
            list.addCell(user.getLastName());
            list.addCell(String.valueOf(user.getBirthday()));
            list.addCell(user.getMobile());
            list.addCell(user.getEmail());
            list.addCell(user.getAddress() + "," + user.getCity() + "," + user.getCountry());
            list.addCell(user.getUsername());
            list.addCell(user.getUserType());
        }
        document.add(list);
        
        Paragraph p2  = new Paragraph("Generated By: "+author);
        p2.setAlignment(Element.ALIGN_RIGHT);
        document.add(p2);
        //document.close();
    }
}

class HeaderAndFooterPdfPageEventHelper extends PdfPageEventHelper {

    @Override
    public void onEndPage(PdfWriter pdfWriter, Document document) {
        try {
            System.out.println("onEndPage() method > Writing footer in file");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            PdfPTable table = new PdfPTable(3);
            table.setWidths(new int[]{24, 24, 2});
            table.getDefaultCell().setFixedHeight(20);
            table.getDefaultCell().setBorder(Rectangle.TOP);
            PdfPCell cell = new PdfPCell();
            cell.setBorder(0);
            cell.setBorderWidthTop(1);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPhrase(new Phrase("Generated at " + dtf.format(now),new Font(Font.FontFamily.COURIER, 10, Font.NORMAL)));
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(0);
            cell.setBorderWidthTop(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPhrase(new Phrase(String.valueOf(pdfWriter.getPageNumber())));
            table.addCell(cell);

            cell = new PdfPCell();
            cell.setBorder(0);
            cell.setBorderWidthTop(1);
            table.addCell(cell);
            table.setTotalWidth(document.getPageSize().getWidth()
                    - document.leftMargin() - document.rightMargin());
            table.writeSelectedRows(0, -1, document.leftMargin(),
                    document.bottomMargin() - 15, pdfWriter.getDirectContent());
        } catch (DocumentException ex) {
            Logger.getLogger(HeaderAndFooterPdfPageEventHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
