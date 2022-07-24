package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.entity.Category;
import models.entity.Courier;
import models.exception.DatabaseException;
import models.util.DatabaseHelper;

public class CourierDAO{
    
    DatabaseHelper db;
    
    public CourierDAO(){
        db = new DatabaseHelper();
    }
    
    public CourierDAO(Connection con){
        db = new DatabaseHelper();
        DatabaseHelper.setCon(con);
    }
    
    public List<Courier> getCouriers() throws DatabaseException{
        List<Courier> list = new ArrayList<Courier>();
        try{
            String qString = "SELECT * FROM COURIER";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new Courier(rs.getInt("ID"), rs.getString("COURIERNAME"), rs.getString("DESCRIPTION"), rs.getFloat("PRICE")));
            }
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public Courier getCourier(int courierId) throws DatabaseException{
        Courier courier = null;
        try{
            String qString = "SELECT * FROM COURIER WHERE ID=?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, courierId);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                courier = new Courier(rs.getInt("ID"), rs.getString("COURIERNAME"), rs.getString("DESCRIPTION"), rs.getFloat("PRICE"));
            }
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return courier;
    }
    
    public boolean addCourier(Courier c) throws DatabaseException{
        try{
            String qString = "INSERT INTO COURIER (COURIERNAME, DESCRIPTION, PRICE) VALUES (?,?,?)";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setString(1, c.getCourierName());
            ps.setString(2, c.getDescription());
            ps.setFloat(3, c.getPrice());
            return ps.execute();
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
    public boolean editCourier(Courier c) throws DatabaseException{
        try{
            String qString = "UPDATE COURIER SET COURIERNAME=?, DESCRIPTION=?, PRICE=? WHERE ID=?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setString(1, c.getCourierName());
            ps.setString(2, c.getDescription());
            ps.setFloat(3, c.getPrice());
            ps.setInt(4, c.getId());
            return ps.execute();
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
    public boolean deleteCourier(int courierId) throws DatabaseException{
        try{
            String qString = "DELETE FROM COURIER WHERE ID=?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, courierId);
            return ps.execute();
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}