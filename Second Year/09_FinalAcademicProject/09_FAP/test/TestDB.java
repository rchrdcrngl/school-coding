
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.dao.*;
import models.exception.DatabaseException;
import models.util.DatabaseHelper;
import models.entity.*;
import models.util.Report;


public class TestDB{
    public static void main (String[] args) throws DocumentException{
        try {
            //String className, String url, String username, String password
            DatabaseHelper db = new DatabaseHelper("org.apache.derby.jdbc.ClientDriver","jdbc:derby://localhost:1527/shopdb","app","app");
            ProductDAO pd = new ProductDAO();
            UsersDAO ud = new UsersDAO();
            OrderDAO od = new OrderDAO();
            Report r = new Report(new FileOutputStream("invoice.pdf"),"Anna");
            r.generateOrderInvoice(od.userGetOrderInvoiceData(2));
            r.getDocument().close();
            
            Report r2 = new Report(new FileOutputStream("stock.pdf"),"John");
            r2.generateStockList(pd.getStockList());
            r2.getDocument().close();
            
            Report r3 = new Report(new FileOutputStream("users.pdf"),"Chris");
            r3.generateGuestList(ud.getAdminUserReportData());
            r3.getDocument().close();
        } catch (DatabaseException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}