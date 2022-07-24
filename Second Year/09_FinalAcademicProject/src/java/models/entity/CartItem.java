package models.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CartItem{
    Integer id;
    Product product;
    Integer cartId;
    Float price;
    int quantity;
    boolean active;
    Date createdAt;
    Date updatedAt;

    public int getId() {
        return id;
    }

    public int getProductId() {
        return product.getId();
    }

    public int getCartId() {
        return cartId;
    }

    public Float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isActive() {
        return active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Product getProduct() {
        return product;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
    public CartItem(int productId, String productName, int quantity, Float price){
        this.product = new Product();
        this.product.setId(productId);
        this.product.setTitle(productName);
        this.price = price;
        this.quantity = quantity;
    }
    public CartItem(int productId, int cartId, Float price, int quantity, boolean active) {
        this.product = new Product(productId);
        this.cartId = cartId;
        this.price = price;
        this.quantity = quantity;
        this.active = active;
    }

    public CartItem(int id, int productId, int cartId, Float price, int quantity, boolean active) {
        this.id = id;
        this.product = new Product(productId);
        this.cartId = cartId;
        this.price = price;
        this.quantity = quantity;
        this.active = active;
    }
    
    //CART_ITEM.ID AS CARTID, PRODUCTS.ID AS PRODUCTID, PRODUCTS.TITLE, IMG.IMGPATH, CART_ITEM.QUANTITY, CART_ITEM.PRICE, PRODUCTS.SLUG, PRODUCTS.STOCK, PRODUCTS.ACTIVE AS PRODUCT_ACTIVE 
    public CartItem(int id, int productId, String productTitle, String imgpath, int quantity, Float price, String slug, int stock, boolean active){
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.active = active;
        this.product = new Product();
        product.setId(productId);
        product.setTitle(productTitle);
        product.setSlug(slug);
        product.setProductImg(imgpath);
        product.setStock(stock);
    }
    
}