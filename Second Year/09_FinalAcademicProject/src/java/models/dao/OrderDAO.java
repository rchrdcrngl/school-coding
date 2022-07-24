package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.entity.Courier;
import models.entity.Order;
import models.entity.OrderItem;
import models.entity.Product;
import models.entity.User;
import models.exception.DatabaseException;
import models.util.DatabaseHelper;

public class OrderDAO{
    DatabaseHelper db;
    
    public OrderDAO(){
        db = new DatabaseHelper();
    }
    
    public OrderDAO(Connection con){
        db = new DatabaseHelper();
        DatabaseHelper.setCon(con);
    }
    
    public Order viewOrder(int orderId) throws DatabaseException, NullPointerException{
        Order order = null;
        List<Product> list = new ArrayList<Product>();
        try{
            //Get Order Details
            String qString = "SELECT ORDERS.*, COURIER.COURIERNAME, COURIER.PRICE AS COURIER_PRICE FROM ORDERS INNER JOIN COURIER ON COURIER.ID = ORDERS.COURIERID WHERE ORDERS.ID = ?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("MOBILE"), rs.getString("EMAIL"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("COUNTRY"), rs.getString("ZIP_CODE"));
                order = new Order(
                    rs.getInt("ID"), rs.getInt("USERID"), rs.getInt("STATUS"), rs.getFloat("SUBTOTAL"), new Courier(rs.getInt("COURIERID"), rs.getString("COURIERNAME"), rs.getFloat("COURIER_PRICE")), rs.getString("TRACKINGNO"), rs.getFloat("TOTAL"), user
                );
            }
            rs.close();
            ps.close();
            //Get Order Items
            String qString2 = "SELECT ORDER_ITEM.ID, PRODUCTS.TITLE, ORDER_ITEM.QUANTITY, ORDER_ITEM.PRICE, PRODUCTS.SLUG, PRODUCTS.IMG FROM ORDER_ITEM INNER JOIN PRODUCTS ON ORDER_ITEM.PRODUCTID = PRODUCTS.ID WHERE ORDERID=?";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString2);
            ps2.setInt(1, orderId);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                list.add(new Product(rs2.getInt("ID"),rs2.getString("TITLE"),rs2.getInt("QUANTITY"),rs2.getFloat("PRICE"),rs2.getString("SLUG"), rs2.getString("IMG")));
            }
            order.setProducts(list);
            rs2.close();
            ps2.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }catch( NullPointerException e){
            throw e;
        }
        return order;
    }
    
    public List<Order> userGetPendingOrders(int userId) throws DatabaseException{
        List<Order> list = new ArrayList<Order>();
        try{
            //Get Order Details
            String qString = "SELECT * FROM ORDERS WHERE USERID=? AND (STATUS > -1 AND STATUS <2)";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("MOBILE"), rs.getString("EMAIL"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("COUNTRY"), rs.getString("ZIP_CODE"));
                list.add(new Order(
                    rs.getInt("ID"), rs.getInt("USERID"), rs.getInt("STATUS"), rs.getFloat("SUBTOTAL"), new Courier(rs.getInt("COURIERID")), rs.getString("TRACKINGNO"), rs.getFloat("TOTAL"), user
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public List<Order> userGetCompletedOrders(int userId) throws DatabaseException{
        List<Order> list = new ArrayList<Order>();
        try{
            //Get Order Details
            String qString = "SELECT * FROM ORDERS WHERE USERID=? AND STATUS = 2";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("MOBILE"), rs.getString("EMAIL"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("COUNTRY"), rs.getString("ZIP_CODE"));
                list.add(new Order(
                    rs.getInt("ID"), rs.getInt("USERID"), rs.getInt("STATUS"), rs.getFloat("SUBTOTAL"), new Courier(rs.getInt("COURIERID")), rs.getString("TRACKINGNO"), rs.getFloat("TOTAL"), user
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public List<Order> userGetCancelledOrders(int userId) throws DatabaseException{
        List<Order> list = new ArrayList<Order>();
        try{
            //Get Order Details
            String qString = "SELECT * FROM ORDERS WHERE USERID=? AND STATUS = -1";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("MOBILE"), rs.getString("EMAIL"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("COUNTRY"), rs.getString("ZIP_CODE"));
                list.add(new Order(
                    rs.getInt("ID"), rs.getInt("USERID"), rs.getInt("STATUS"), rs.getFloat("SUBTOTAL"), new Courier(rs.getInt("COURIERID")), rs.getString("TRACKINGNO"), rs.getFloat("TOTAL"), user
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public List<Order> userGetOrders(int userId) throws DatabaseException{
        List<Order> list = new ArrayList<Order>();
        try{
            //Get Order Details
            String qString = "SELECT * FROM ORDERS WHERE USERID=?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("MOBILE"), rs.getString("EMAIL"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("COUNTRY"), rs.getString("ZIP_CODE"));
                list.add(new Order(
                    rs.getInt("ID"), rs.getInt("USERID"), rs.getInt("STATUS"), rs.getFloat("SUBTOTAL"), new Courier(rs.getInt("COURIERID")), rs.getString("TRACKINGNO"), rs.getFloat("TOTAL"), user
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public boolean userReceiveOrder(int orderId) throws DatabaseException{
        try{
           String qString = "UPDATE ORDERS SET STATUS=2, UPDATEDAT = CURRENT TIMESTAMP WHERE ID=?";
           PreparedStatement ps = DatabaseHelper.getCon().prepareStatement(qString);
           ps.setInt(1, orderId);
           return ps.execute();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    public boolean empEditOrderTracking(int orderId, String trackingNo) throws DatabaseException{
        try{
           String qString = "UPDATE ORDERS SET TRACKINGNO=?, STATUS=1, UPDATEDAT = CURRENT TIMESTAMP WHERE ID=?";
           PreparedStatement ps = DatabaseHelper.getCon().prepareStatement(qString);
           ps.setString(1, trackingNo);
           ps.setInt(2, orderId);
           return ps.execute();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
    public boolean userCancelOrder(int orderId) throws DatabaseException{
        try{
           String qString = "UPDATE ORDERS SET STATUS=-1, UPDATEDAT = CURRENT TIMESTAMP WHERE ID=?";
           PreparedStatement ps = DatabaseHelper.getCon().prepareStatement(qString);
           ps.setInt(1, orderId);
           return ps.execute();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
    public int userPlaceOrder(User user, Float subtotal, int courierId, Float total, List<OrderItem> items) throws DatabaseException{
        Integer orderId = 0;
        try{
            //Create Order
           String qString = "INSERT INTO ORDERS (USERID, SUBTOTAL, COURIERID, TOTAL, FIRSTNAME, LASTNAME, MOBILE, EMAIL, ADDRESS, CITY, COUNTRY, ZIP_CODE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
           PreparedStatement ps = DatabaseHelper.getCon().prepareStatement(qString, new String[]{"ID"});
           ps.setInt(1, user.getId());
           ps.setFloat(2, subtotal);
           ps.setInt(3, courierId);
           ps.setFloat(4, total);
           ps.setString(5, user.getFirstName());
           ps.setString(6, user.getLastName());
           ps.setString(7, user.getMobile());
           ps.setString(8, user.getEmail());
           ps.setString(9, user.getAddress());
           ps.setString(10, user.getCity());
           ps.setString(11, user.getCountry());
           ps.setString(12, user.getZip_code());
           ps.execute();
           ResultSet rs = ps.getGeneratedKeys();
           if (rs.next()) {
               orderId = rs.getInt(1);
           }
           System.out.println(orderId);
           //Add Order Items, Delete items from Cart, Update Stock
           PreparedStatement ps2 = DatabaseHelper.getCon().prepareStatement("INSERT INTO ORDER_ITEM (PRODUCTID, ORDERID, PRICE, QUANTITY) VALUES (?,?,?,?)");
           
           PreparedStatement ps4 = DatabaseHelper.getCon().prepareStatement("UPDATE PRODUCTS SET STOCK = (STOCK-?), UPDATEDAT = CURRENT TIMESTAMP WHERE PRODUCTS.ID = ?");
           for(OrderItem item : items){
               //Add Order Item
               System.out.println("order product: " + item.getProductId());
               ps2.setInt(1, item.getProductId());
               ps2.setInt(2,orderId);
               ps2.setFloat(3, item.getPrice());
               ps2.setInt(4, item.getQuantity());
               ps2.execute();
               System.out.println("OK: " + item.getProductId());
               
               //Update Product Stock
               ps4.setInt(1, item.getQuantity());
               ps4.setInt(2, item.getProductId());
               ps4.execute();
               System.out.println("update product to minus " + item.getQuantity());
           }
           PreparedStatement ps3 = DatabaseHelper.getCon().prepareStatement("UPDATE CART SET ACTIVE = FALSE WHERE ID=(SELECT ID FROM CART WHERE USERID = ? AND ACTIVE = TRUE)");
           ps3.setInt(1, user.getId());
           ps3.execute();
           return orderId;
           
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        
    }
    
    public Order userGetOrderInvoiceData(int orderId) throws DatabaseException{
        Order order = null;
        List<Product> list = new ArrayList<Product>();
        try{
            //Get Order Details
            String qString = "SELECT ORDERS.*, COURIER.COURIERNAME, COURIER.PRICE AS COURIER_PRICE FROM ORDERS INNER JOIN COURIER ON COURIER.ID = ORDERS.COURIERID WHERE ORDERS.ID = ?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("MOBILE"), rs.getString("EMAIL"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("COUNTRY"), rs.getString("ZIP_CODE"));
                order = new Order(
                    rs.getInt("ID"), rs.getInt("USERID"), rs.getInt("STATUS"), rs.getFloat("SUBTOTAL"), new Courier(rs.getInt("COURIERID"), rs.getString("COURIERNAME"), rs.getFloat("COURIER_PRICE")), rs.getString("TRACKINGNO"), rs.getFloat("TOTAL"), user
                );
                order.setCreatedAt(rs.getDate("CREATEDAT"));
            }
            rs.close();
            ps.close();
            //Get Order Items
            String qString2 = "SELECT PRODUCTS.TITLE, ORDER_ITEM.QUANTITY, ORDER_ITEM.PRICE FROM ORDER_ITEM INNER JOIN PRODUCTS ON ORDER_ITEM.PRODUCTID = PRODUCTS.ID WHERE ORDERID=?";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString2);
            ps2.setInt(1, orderId);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                list.add(new Product(rs2.getString("TITLE"),rs2.getInt("QUANTITY"),rs2.getFloat("PRICE")));
            }
            order.setProducts(list);
            rs2.close();
            ps2.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return order;
    }
    
    public Order userGetOrderDetails(int orderId, int userId) throws DatabaseException, NullPointerException{
        Order order = null;
        List<Product> list = new ArrayList<Product>();
        try{
            //Get Order Details
            String qString = "SELECT ORDERS.*, COURIER.COURIERNAME, COURIER.PRICE AS COURIER_PRICE FROM ORDERS INNER JOIN COURIER ON COURIER.ID = ORDERS.COURIERID WHERE ORDERS.ID = ? AND ORDERS.USERID=?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, orderId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("MOBILE"), rs.getString("EMAIL"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("COUNTRY"), rs.getString("ZIP_CODE"));
                order = new Order(
                    rs.getInt("ID"), rs.getInt("USERID"), rs.getInt("STATUS"), rs.getFloat("SUBTOTAL"), new Courier(rs.getInt("COURIERID"), rs.getString("COURIERNAME"), rs.getFloat("COURIER_PRICE")), rs.getString("TRACKINGNO"), rs.getFloat("TOTAL"), user
                );
                order.setCreatedAt(rs.getDate("CREATEDAT"));
            }
            rs.close();
            ps.close();
            if(order==null) throw new DatabaseException("Record does not exist.");
            //Get Order Items
            String qString2 = "SELECT PRODUCTS.TITLE, PRODUCTS.SLUG, PRODUCTS.IMG, ORDER_ITEM.QUANTITY, ORDER_ITEM.PRICE FROM ORDER_ITEM INNER JOIN PRODUCTS ON ORDER_ITEM.PRODUCTID = PRODUCTS.ID WHERE ORDERID=?";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString2);
            ps2.setInt(1, orderId);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                Product p = new Product(rs2.getString("TITLE"),rs2.getInt("QUANTITY"),rs2.getFloat("PRICE"));
                p.setSlug(rs2.getString("SLUG"));
                p.setProductImg(rs2.getString("IMG"));
                list.add(p);
            }
            order.setProducts(list);
            rs2.close();
            ps2.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }catch( NullPointerException e){
            throw e;
        }
        return order;
    }
    
    public List<Order> empGetToProcessOrders() throws DatabaseException{
        List<Order> list = new ArrayList<Order>();
        try{
            //Get Order Details
            String qString = "SELECT * FROM ORDERS WHERE STATUS = 0";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("MOBILE"), rs.getString("EMAIL"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("COUNTRY"), rs.getString("ZIP_CODE"));
                list.add(new Order(
                    rs.getInt("ID"), rs.getInt("USERID"), rs.getInt("STATUS"), rs.getFloat("SUBTOTAL"), new Courier(rs.getInt("COURIERID")), rs.getString("TRACKINGNO"), rs.getFloat("TOTAL"), user
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public List<Order> empGetShippedOrders() throws DatabaseException{
        List<Order> list = new ArrayList<Order>();
        try{
            //Get Order Details
            String qString = "SELECT * FROM ORDERS WHERE STATUS = 1";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("MOBILE"), rs.getString("EMAIL"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("COUNTRY"), rs.getString("ZIP_CODE"));
                list.add(new Order(
                    rs.getInt("ID"), rs.getInt("USERID"), rs.getInt("STATUS"), rs.getFloat("SUBTOTAL"), new Courier(rs.getInt("COURIERID")), rs.getString("TRACKINGNO"), rs.getFloat("TOTAL"), user
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public Order empGetOrderSlipData(int orderId) throws DatabaseException{
        Order order = null;
        List<Product> list = new ArrayList<Product>();
        try{
            //Get Order Details
            String qString = "SELECT ORDERS.*, COURIER.COURIERNAME, COURIER.PRICE AS COURIER_PRICE FROM ORDERS INNER JOIN COURIER ON COURIER.ID = ORDERS.COURIERID WHERE ORDERS.ID = ?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("MOBILE"), rs.getString("EMAIL"), rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("COUNTRY"), rs.getString("ZIP_CODE"));
                order = new Order(
                    rs.getInt("ID"), rs.getInt("USERID"), rs.getInt("STATUS"), rs.getFloat("SUBTOTAL"), new Courier(rs.getInt("COURIERID"), rs.getString("COURIERNAME"), rs.getFloat("COURIER_PRICE")), rs.getString("TRACKINGNO"), rs.getFloat("TOTAL"), user
                );
            }
            rs.close();
            ps.close();
            //Get Order Items
            String qString2 = "SELECT PRODUCTS.TITLE, ORDER_ITEM.QUANTITY, ORDER_ITEM.PRICE AS TOTAL, PRODUCTS.SKU FROM ORDER_ITEM INNER JOIN PRODUCTS ON ORDER_ITEM.PRODUCTID = PRODUCTS.ID WHERE ORDERID=?";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString2);
            ps2.setInt(1, orderId);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                list.add(new Product(rs2.getString("TITLE"),rs2.getInt("QUANTITY"),rs2.getFloat("TOTAL"),rs2.getString("SKU")));
            }
            order.setProducts(list);
            rs2.close();
            ps2.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return order;
    }
    
    public List<Order> adminGetOrders() throws DatabaseException{
        List<Order> list = new ArrayList<Order>();
        try{
            //Get Order Details
            String qString = "SELECT * FROM ORDERS";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setFirstName(rs.getString("FIRSTNAME"));
                user.setLastName(rs.getString("LASTNAME"));
                user.setMobile(rs.getString("MOBILE"));
                user.setCity(rs.getString("CITY"));
                user.setCountry(rs.getString("COUNTRY"));
                list.add(new Order(
                    rs.getInt("ID"), rs.getInt("USERID"), rs.getInt("STATUS"), rs.getFloat("SUBTOTAL"), new Courier(rs.getInt("COURIERID")), rs.getString("TRACKINGNO"), rs.getFloat("TOTAL"), user
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
}