package model.record;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.exception.*;

public class RecordModel{
    static Connection con;
    public RecordModel(String className, String url, String username, String password) throws DatabaseException {
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqle) {
            throw new DatabaseException("SQL Error: Connection is null.");
        } catch (ClassNotFoundException nfe) {
            throw new DatabaseException("Class Error: Connection is null.");
        }
    }
    
    public RecordModel(){
        
    }

    public Connection getConnection() {
        return con;
    }
    
    public List<Record> manufacturerSearch(String q) throws DatabaseException{
        List<Record> res = new ArrayList<Record>();
        try{
            //Retrieves the data from database
            String qString = "SELECT * FROM RECORDS INNER JOIN USERS ON USERS.ID_NO = RECORDS.ID_NO WHERE VACCINE_MANUFACTURER=? ORDER BY RECORDS.ID_NO, RECORDS.DOSAGE_NO";
            PreparedStatement ps = con.prepareStatement(qString);
            ps.setString(1, q);
            ResultSet records = ps.executeQuery();
            
            //Parses the ResultSet object and adds content to list as Friend object
            while(records.next()){
                res.add(new Record(records.getInt("RECORD_NO"),records.getInt("ID_NO"),records.getString("FIRSTNAME"), records.getString("LASTNAME"), records.getInt("DOSAGE_NO"), records.getDate("DOSAGE_DATE"), records.getString("VACCINE_MANUFACTURER"), records.getString("DOSAGE_LOTNO"), records.getString("SITE_CITY"), records.getString("SITE_COUNTRY")));
            }
            
            records.close();
            ps.close();
        }catch(SQLException e){
            throw new DatabaseException(e.getMessage());
        }
        return res;
    }
    
    public List<Record> idSearch(int idNo) throws DatabaseException{
        List<Record> res = new ArrayList<Record>();
        try{
            //Retrieves the data from database
            String qString = "SELECT * FROM RECORDS INNER JOIN USERS ON USERS.ID_NO = RECORDS.ID_NO WHERE RECORDS.ID_NO=? ORDER BY RECORDS.ID_NO, RECORDS.DOSAGE_NO";
            PreparedStatement ps = con.prepareStatement(qString);
            ps.setInt(1, idNo);
            ResultSet records = ps.executeQuery();
            
            //Parses the ResultSet object and adds content to list as Friend object
            while(records.next()){
                res.add(new Record(records.getInt("RECORD_NO"),records.getInt("ID_NO"),records.getString("FIRSTNAME"), records.getString("LASTNAME"),records.getInt("DOSAGE_NO"), records.getDate("DOSAGE_DATE"), records.getString("VACCINE_MANUFACTURER"), records.getString("DOSAGE_LOTNO"), records.getString("SITE_CITY"), records.getString("SITE_COUNTRY")));
            }
            
            records.close();
            ps.close();
        }catch(SQLException e){
            throw new DatabaseException(e.getMessage());
        }
        return res;
    }
    
    public List<Record> nameSearch(String name) throws DatabaseException{
        List<Record> res = new ArrayList<Record>();
        try{
            //Retrieves the data from database
            String qString = "SELECT * FROM RECORDS INNER JOIN USERS ON USERS.ID_NO = RECORDS.ID_NO WHERE USERS.FIRSTNAME=? OR USERS.LASTNAME=? OR ((USERS.FIRSTNAME||' '||USERS.LASTNAME)=?) ORDER BY RECORDS.ID_NO, RECORDS.DOSAGE_NO";
            PreparedStatement ps = con.prepareStatement(qString);
            ps.setString(1, name);
            ps.setString(2, name);
            ps.setString(3, name);
            ResultSet records = ps.executeQuery();
            
            //Parses the ResultSet object and adds content to list as Friend object
            while(records.next()){
                res.add(new Record(records.getInt("RECORD_NO"),records.getInt("ID_NO"),records.getString("FIRSTNAME"), records.getString("LASTNAME"),records.getInt("DOSAGE_NO"), records.getDate("DOSAGE_DATE"), records.getString("VACCINE_MANUFACTURER"), records.getString("DOSAGE_LOTNO"), records.getString("SITE_CITY"), records.getString("SITE_COUNTRY")));
            }
            
            records.close();
            ps.close();
        }catch(SQLException e){
            throw new DatabaseException(e.getMessage());
        }
        return res;
    }
    
    public List<Record> getAllRecords() throws DatabaseException{
        List<Record> res = new ArrayList<Record>();
        try{
            //Retrieves the data from database
            String qString = "SELECT * FROM RECORDS INNER JOIN USERS ON USERS.ID_NO = RECORDS.ID_NO ORDER BY RECORDS.ID_NO, RECORDS.DOSAGE_NO";
            PreparedStatement ps = con.prepareStatement(qString);
            ResultSet records = ps.executeQuery();
            
            //Parses the ResultSet object and adds content to list as Friend object
            while(records.next()){
                res.add(new Record(records.getInt("RECORD_NO"),records.getInt("ID_NO"),records.getString("FIRSTNAME"), records.getString("LASTNAME"),records.getInt("DOSAGE_NO"), records.getDate("DOSAGE_DATE"), records.getString("VACCINE_MANUFACTURER"), records.getString("DOSAGE_LOTNO"), records.getString("SITE_CITY"), records.getString("SITE_COUNTRY")));
            }
            
            records.close();
            ps.close();
        }catch(SQLException e){
            throw new DatabaseException(e.getMessage());
        }
        return res;
    }
    
    public void addRecord(int ID_NO, int DOSAGE_NO, Date DOSAGE_DATE, String VACCINE_MANUFACTURER, String DOSAGE_LOTNO, String SITE_CITY, String SITE_COUNTRY) throws DatabaseException{
        try{
            String qString = "INSERT INTO RECORDS (ID_NO, DOSAGE_NO, DOSAGE_DATE, VACCINE_MANUFACTURER, DOSAGE_LOTNO, SITE_CITY, SITE_COUNTRY) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(qString);
            ps.setInt(1, ID_NO);
            ps.setInt(2, DOSAGE_NO);
            ps.setDate(3, DOSAGE_DATE);
            ps.setString(4, VACCINE_MANUFACTURER);
            ps.setString(5, DOSAGE_LOTNO);
            ps.setString(6, SITE_CITY);
            ps.setString(7, SITE_COUNTRY);
            ps.execute();
        } catch(SQLException e){
            throw new DatabaseException(e.getMessage());
        }
    }
    
    public void editRecord(int RECORD_NO, int ID_NO, int DOSAGE_NO, Date DOSAGE_DATE, String VACCINE_MANUFACTURER, String DOSAGE_LOTNO, String SITE_CITY, String SITE_COUNTRY) throws DatabaseException{
        try{
            String qString = "UPDATE RECORDS SET ID_NO=?, DOSAGE_NO=?, DOSAGE_DATE=?, VACCINE_MANUFACTURER=?, DOSAGE_LOTNO=?, SITE_CITY=?, SITE_COUNTRY=? WHERE RECORD_NO=?";
            PreparedStatement ps = con.prepareStatement(qString);
            ps.setInt(1, ID_NO);
            ps.setInt(2, DOSAGE_NO);
            ps.setDate(3, DOSAGE_DATE);
            ps.setString(4, VACCINE_MANUFACTURER);
            ps.setString(5, DOSAGE_LOTNO);
            ps.setString(6, SITE_CITY);
            ps.setString(7, SITE_COUNTRY);
            ps.setInt(8,RECORD_NO);
            ps.execute();
        } catch(SQLException e){
            throw new DatabaseException(e.getMessage());
        }
    }
    
    public void deleteRecord(int RECORD_NO) throws DatabaseException{
        try{
            String qString = "DELETE FROM RECORDS WHERE RECORD_NO=?";
            PreparedStatement ps = con.prepareStatement(qString);
            ps.setInt(1, RECORD_NO);
            ps.execute();
        } catch(SQLException e){
            throw new DatabaseException(e.getMessage());
        }
    }
}