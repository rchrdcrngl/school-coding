package model.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.exception.DatabaseException;

public class AccountModel {

    Connection con;

    public AccountModel(String className, String url, String username, String password) throws DatabaseException{
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqle) {
            throw new DatabaseException("SQL Error: Connection is null. Try again");
        } catch (ClassNotFoundException nfe) {
            throw new DatabaseException("Class Error: Connection is null. Try again");
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {

        }
    }
    
    public int authorize(Account u) throws DatabaseException {
        int auth = -1;
        String username = u.getUsername().trim();
        String password = u.getPassword().trim();
        String dbUname;
        String dbPword;
        String userType;
        String name;
        int idNO;

        try {
            //Retrieves the data from database
            String qString = "SELECT * FROM ACCOUNTS INNER JOIN USERS ON USERS.ID_NO = ACCOUNTS.ID_NO WHERE (USERNAME = ? AND PASSWORD = ?) OR USERNAME = ?";
            PreparedStatement ps = con.prepareStatement(qString);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, username);
            ResultSet records = ps.executeQuery();

            //Parses the ResultSet object and checks for record with that username and password
            while (records.next()) {
                
                //Get result's username and password
                dbUname = records.getString("USERNAME");
                dbPword = records.getString("PASSWORD");
                idNO = records.getInt("ID_NO");
                userType = records.getString("TYPE");
                name = records.getString("FIRSTNAME") +" "+ records.getString("LASTNAME");
                
                //If there is no returned result, break from while loop, set int as -1
                if(dbUname==null || dbPword==null){
                    auth = -1;
                    break;
                }

                //If both username and password are correct, return 1
                if (username.equals(dbUname) && password.equals(dbPword)) {
                    u.setIDNO(idNO);
                    u.setType(userType.trim());
                    u.setName(name.trim());
                    auth = 1;
                    
                //If only username is correct, return 0
                } else if (username.equals(dbUname) && !password.equals(dbPword)) {
                    auth = 0;
                    
                //Else, return -1 for unsuccessful login
                } else {
                    auth = -1;
                }
            }

            records.close();
            ps.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return auth;
    }
}
