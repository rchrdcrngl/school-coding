package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCModel {
    
    Connection con;
    
    public JDBCModel(String className, String url, String username, String password) throws SQLException, ClassNotFoundException{
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqle) {
            throw sqle;
        } catch (ClassNotFoundException nfe) {
            throw nfe;
        }
    }
    
    public Connection getConnection(){
        return con;
    }
    
    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException e) {
            
        }
    }
    
}