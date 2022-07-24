package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.entity.Category;
import models.entity.ProductType;
import models.exception.DatabaseException;
import models.util.DatabaseHelper;

public class ProductTypeDAO{
    
    DatabaseHelper db;
    
    public ProductTypeDAO(){
        db = new DatabaseHelper();
    }
    
    public ProductTypeDAO(Connection con){
        db = new DatabaseHelper();
        DatabaseHelper.setCon(con);
    }
    
    public List<ProductType> getProductTypes() throws DatabaseException{
        List<ProductType> list = new ArrayList<ProductType>();
        try{
            String qString = "SELECT * FROM PRODUCT_TYPE";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new ProductType(rs.getInt("ID"), rs.getString("TITLE")));
            }
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public ProductType getProductType(int ProductTypeId) throws DatabaseException, NullPointerException{
        ProductType ProductType = null;
        try{
            String qString = "SELECT * FROM PRODUCT_TYPE WHERE ID=?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, ProductTypeId);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProductType = new ProductType(rs.getInt("ID"), rs.getString("TITLE"));
            }
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }catch( NullPointerException e){
            throw e;
        }
        return ProductType;
    }
    
    public boolean addProductType(String title) throws DatabaseException{
        try{
            String qString = "INSERT INTO PRODUCT_TYPE (TITLE) VALUES (?)";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setString(1, title);
            return ps.execute();
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
    public boolean deleteProductType(int ProductTypeId) throws DatabaseException{
        try{
            String qString = "DELETE FROM PRODUCT_TYPE WHERE ID=?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, ProductTypeId);
            return ps.execute();
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}