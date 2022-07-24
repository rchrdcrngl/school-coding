package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.entity.CartItem;
import models.entity.Product;
import models.exception.DatabaseException;
import models.util.DatabaseHelper;

public class CartDAO{
    DatabaseHelper db;
    
    public CartDAO(){
        db = new DatabaseHelper();
    }
    
    public CartDAO(Connection con){
        db = new DatabaseHelper();
        DatabaseHelper.setCon(con);
    }
    
    public List<CartItem> retrieveCartItems(int userId) throws DatabaseException{
        List<CartItem> list = new ArrayList<CartItem>();
        try{
            String qString = "SELECT CART_ITEM.ID AS CARTID, PRODUCTS.ID AS PRODUCTID, PRODUCTS.TITLE, PRODUCTS.IMG AS IMGPATH, CART_ITEM.QUANTITY, CART_ITEM.PRICE, PRODUCTS.SLUG, PRODUCTS.STOCK, PRODUCTS.ACTIVE AS PRODUCT_ACTIVE FROM CART INNER JOIN CART_ITEM ON CART_ITEM.CARTID = CART.ID INNER JOIN PRODUCTS ON CART_ITEM.PRODUCTID = PRODUCTS.ID WHERE CART.USERID = ? AND CART.ACTIVE = TRUE";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new CartItem(
                        rs.getInt("CARTID"), rs.getInt("PRODUCTID"), rs.getString("TITLE"), rs.getString("IMGPATH"), rs.getInt("QUANTITY"), rs.getFloat("PRICE"), rs.getString("SLUG"), rs.getInt("STOCK"), rs.getBoolean("PRODUCT_ACTIVE")
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }

    public int addToCart(int userId, int productId, int quantity, Float price) throws DatabaseException{
        int success = 0;
        boolean isCartActive = false;
        boolean isInCart;
        try{
            String qString = "SELECT ID, ACTIVE FROM CART WHERE USERID = ? AND ACTIVE = TRUE";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) isCartActive = rs.getBoolean("ACTIVE");
            rs.close();
            ps.close();
            System.out.println(isCartActive);
            if(isCartActive==false){
                String qString2 = "INSERT INTO CART (USERID, ACTIVE) VALUES (?, TRUE)";
                PreparedStatement ps2 = db.getCon().prepareStatement(qString2);
                ps2.setInt(1, userId);
                ps2.execute();
                ps2.close();
            }
            //
            System.out.println("test0");
            String qString0 = "SELECT ID, QUANTITY FROM CART_ITEM WHERE CARTID=(SELECT ID FROM CART WHERE USERID=? AND ACTIVE=TRUE) AND PRODUCTID=? AND ACTIVE = TRUE";
            PreparedStatement ps0 = db.getCon().prepareStatement(qString0);
            ps0.setInt(1, userId);
            ps0.setInt(2, productId);
            ResultSet rs0 = ps0.executeQuery();
            System.out.println("test1");
            if(rs0.next()){
                System.out.println("Already in cart");
                return editItemQty(rs0.getInt("ID"), quantity+rs0.getInt("QUANTITY"), price);
            }else{
                rs0.close();
                ps0.close();
                System.out.println("ADD TO CART");
                String qString3 = "INSERT INTO CART_ITEM (PRODUCTID, CARTID, PRICE, QUANTITY, UPDATEDAT) VALUES (?,(SELECT ID FROM CART WHERE USERID=? AND ACTIVE=TRUE),?,?, CURRENT TIMESTAMP)";
                PreparedStatement ps3 = db.getCon().prepareStatement(qString3);
                ps3.setInt(1, productId);
                ps3.setInt(2, userId);
                ps3.setFloat(3, price);
                ps3.setInt(4, quantity);
                ps3.execute();
                ps3.close();
            }
            success=1;
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }
    
    public int deleteItem(int cartItemId) throws DatabaseException{
        int success = 0;
        try{
            String qString = "DELETE FROM CART_ITEM WHERE ID = ?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, cartItemId);
            ps.execute();
            ps.close();
            success=1;
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }
    
    public int editItemQty(int cartItemId, int quantity, Float price) throws DatabaseException{
        int success = 0;
        try{
            String qString = "UPDATE CART_ITEM SET QUANTITY = ?, PRICE=?, UPDATEDAT = CURRENT TIMESTAMP WHERE ID = ?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, quantity);
            ps.setFloat(2, price);
            ps.setInt(3, cartItemId);
            ps.execute();
            ps.close();
            success=1;
            System.out.println("item qty updated");
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }

}