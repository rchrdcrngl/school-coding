package models.entity;

import java.text.DecimalFormat;

public class OrderItem{
    Integer id;
    Integer productId;
    Integer orderId;
    Float price;
    Integer quantity;
    Integer cartId;

    public OrderItem(int productId, int orderId, Float price, int quantity) {
        this.productId = productId;
        this.orderId = orderId;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
        this.quantity = quantity;
    }
    
    public OrderItem(int productId, int orderId, Float price, int quantity, int cartId) {
        this.productId = productId;
        this.orderId = orderId;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
        this.quantity = quantity;
        this.cartId = cartId;
    }
    
    public OrderItem(int productId, int quantity, Float price) {
        this.productId = productId;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
        this.quantity = quantity;
    }

    public OrderItem(int id, int productId, int orderId, Float price, int quantity) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public Float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    
    
}