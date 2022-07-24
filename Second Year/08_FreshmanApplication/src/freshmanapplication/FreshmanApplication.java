
package freshmanapplication;

public class FreshmanApplication {
    final static String curl = "jdbc:mysql://localhost:3306/freshman_application?autoReconnect=true&useSSL=false";
    final static String username = "root"; //Replace username
    final static String password = "_@dM1n1StR4t0r."; // Replace password

    public static void main(String[] args) {
        ApplicationForm form = new ApplicationForm();
        form.setVisible(true);
    }
    
}
