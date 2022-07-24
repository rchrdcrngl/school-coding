package models.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import models.exception.DatabaseException;

public class DatabaseHelper{
    private static Connection con;
    
    public DatabaseHelper(){
        
    }
    
    public DatabaseHelper(String className, String url, String username, String password) throws DatabaseException{
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqle) {
            throw new DatabaseException("SQL Error: Connection is null. Try again");
        } catch (ClassNotFoundException nfe) {
            throw new DatabaseException("Class Error: Connection is null. Try again");
        }
    }

    public static Connection getCon()throws DatabaseException {
        if(con==null) throw new DatabaseException("Cannot connect to database at the moment.");
        return con;
    }

    public static void setCon(Connection con) {
        DatabaseHelper.con = con;
    }
    
    
}