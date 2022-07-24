package model.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import model.exception.DatabaseException;
import model.security.Security;

public class AccountModel {

    private static Connection con;

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
    
    public AccountModel(){
        
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
    
    public int login(Account u) throws DatabaseException {
        int auth = -1;
        String username = u.getUsername().trim();
        String password = u.getPassword().trim();
        String dbUname;
        String dbPword;
        String userType;
        String lastname;
        String firstname;

        try {
            //Retrieves the data from database
            String qString = "SELECT * FROM UserDB WHERE USERNAME = ?";
            PreparedStatement ps = con.prepareStatement(qString);
            ps.setString(1, username);
            ResultSet records = ps.executeQuery();

            //Parses the ResultSet object and checks for record with that username and password
            while (records.next()) {
                //Get result's username and password
                dbUname = records.getString("USERNAME");
                
                //If there is no returned result, break from while loop, set int as -1
                if(dbUname==null){
                    auth = -1;
                    break;
                }
                
                dbPword = records.getString("PASSWORD");
                userType = records.getString("TYPE");
                lastname = records.getString("LASTNAME");
                firstname = records.getString("FIRSTNAME");
                
                //Decrypt password from database
                dbPword = Security.decrypt(dbPword);
                
                //If both username and password are correct, return 1
                if (username.equals(dbUname) && password.equals(dbPword)) {
                    u.setType(userType.trim());
                    u.setFirstName(firstname.trim());
                    u.setLastName(lastname.trim());
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
    
    public int signup(Account u) throws DatabaseException {
        int success = -1;
        String username = u.getUsername().trim();
        String password = u.getPassword().trim();
        String userType = u.getType().trim();
        String lastname = u.getLastName().trim();
        String firstname = u.getFirstName().trim();
        int idNO;

        try {
            //Retrieves the data from database
            String qString = "SELECT * FROM UserDB WHERE USERNAME=?";
            String qString2 = "INSERT INTO UserDB(FIRSTNAME, LASTNAME, USERNAME, PASSWORD, TYPE) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(qString);
            ps.setString(1, username);
            ResultSet records = ps.executeQuery();

            //Check if username currently exists in the database
            while (records.next()) {
                //Get result's username
                String uname = records.getString("USERNAME");
                
                //Check if username exists
                if(uname!=null){
                    success = -1;
                    throw new DatabaseException("Username already taken.");
                }
            }
            
            //Add data to database
            PreparedStatement ps2 = con.prepareStatement(qString2);
            ps2.setString(1, firstname);
            ps2.setString(2, lastname);
            ps2.setString(3, username);
            ps2.setString(4, Security.encrypt(password));
            ps2.setString(5, userType);
            ps2.execute();
            
            success = 1;

            records.close();
            ps.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }
    
    public String[][] getAccountTable() throws DatabaseException{
        String[][] table;
        try {
            //Get number of data
            String qString = "SELECT count(*) AS RECORD_COUNT FROM UserDB";
            PreparedStatement ps = con.prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            rs.next();
            table = new String[rs.getInt("RECORD_COUNT")+1][3];
            rs.close();
            ps.close();
            
            table[0][0] = "Name";
            table[0][1] = "Username";
            table[0][2] = "User Type";
            
            qString = "SELECT * FROM UserDB";
            PreparedStatement ps2 = con.prepareStatement(qString);
            ResultSet records = ps2.executeQuery();
            //records.first();
               
            int i=1;
            while (records.next()) {
                if(i<table.length){
                    table[i][0] = records.getString("FIRSTNAME") +" "+records.getString("LASTNAME");
                    table[i][1] = records.getString("USERNAME");
                    table[i][2] = records.getString("TYPE");
                    i++;
                }
            }
         
            records.close();
            ps2.close();
            System.out.println(Arrays.deepToString(table));
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return table;
    }
}
