package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.entity.Category;
import models.entity.Product;
import models.exception.DatabaseException;
import models.util.DatabaseHelper;

public class CategoryDAO{
    
    DatabaseHelper db;
    
    public CategoryDAO(){
        db = new DatabaseHelper();
    }
    
    public CategoryDAO(Connection con){
        db = new DatabaseHelper();
        DatabaseHelper.setCon(con);
    }
    
    public List<Category> getTopCategories() throws DatabaseException{
        List<Category> list = new ArrayList<Category>();
        try{
            String qString = "SELECT * FROM CATEGORY FETCH FIRST 4 ROWS ONLY";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new Category(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("SLUG")));
            }
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public List<Category> getCategories() throws DatabaseException{
        List<Category> list = new ArrayList<Category>();
        try{
            String qString = "SELECT * FROM CATEGORY";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new Category(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("SLUG")));
            }
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    public Category getCategory(int categoryId) throws DatabaseException{
        Category category = null;
        try{
            String qString = "SELECT * FROM CATEGORY WHERE ID=?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                category = new Category(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("SLUG"));
            }
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return category;
    }
    
    public boolean addCategory(Category ctg) throws DatabaseException{
        try{
            String qString = "INSERT INTO CATEGORY (TITLE, SLUG) VALUES (?,?)";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setString(1, ctg.getTitle());
            ps.setString(2, ctg.getSlug());
            return ps.execute();
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
    public boolean editCategory(Category ctg) throws DatabaseException{
        try{
            String qString = "UPDATE CATEGORY SET TITLE=?, SLUG=? WHERE ID=?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setString(1, ctg.getTitle());
            ps.setString(2, ctg.getSlug());
            ps.setInt(3, ctg.getId());
            return ps.execute();
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
    public boolean deleteCategory(int categoryId) throws DatabaseException{
        try{
            String qString = "DELETE FROM CATEGORY WHERE ID=?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, categoryId);
            return ps.execute();
        
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}