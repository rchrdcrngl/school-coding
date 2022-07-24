package models.entity;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;

public class Order{
    Integer id;
    Integer userId;
    Integer status;
    Float subtotal;
    Courier courier;
    String trackingNo;
    Float total;
    User user;
    Date updatedAt;
    Date createdAt;

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    List<Product> products;
    
    public List<Product> getProducts() {
        return products;
    }

    public Order(Integer id, Integer userId, Integer status, Float subtotal, Courier courier, String trackingNo, Float total, User user) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        DecimalFormat df = new DecimalFormat("0.00");
        this.subtotal = Float.valueOf(df.format(subtotal));
        this.courier = courier;
        this.trackingNo = trackingNo;
        this.total = total;
        this.user = user;
    }
    
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    //ORDER_ITEM.ID, PRODUCTS.TITLE, ORDER_ITEM.QUANTITY, ORDER_ITEM.PRICE, PRODUCTS.SLUG, IMG.IMGPATH
    

    public Order(int userId, Float subtotal, Courier courier, String trackingNo, Float total, User user) {
        this.userId = userId;
        DecimalFormat df = new DecimalFormat("0.00");
        this.subtotal = Float.valueOf(df.format(subtotal));
        this.courier = courier;
        this.trackingNo = trackingNo;
        this.total = total;
        this.user = user;
    }

    public Order(int id, int userId, int status, Float subtotal, Courier courier, String trackingNo, Float total, User user, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.subtotal = subtotal;
        this.courier = courier;
        this.trackingNo = trackingNo;
        this.total = total;
        this.user = user;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getStatus() {
        return status;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public Courier getCourier() {
        return courier;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public Float getTotal() {
        return total;
    }

    public User getUser() {
        return user;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}