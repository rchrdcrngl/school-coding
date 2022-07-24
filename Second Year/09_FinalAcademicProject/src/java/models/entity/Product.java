package models.entity;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Product {

    Integer id;
    String title;
    String slug;
    String description;
    ProductType type;
    String sku;
    Float price;
    Integer stock;
    String manufacturer;
    Date createdAt;
    Date updatedAt;
    Date publishedAt;
    boolean active;
    String productImg;
    private List<ProductMeta> meta;
    private List<Category> categories;
    HashSet ctgHash = new HashSet();

    public Date getPublishedAt() {
        return publishedAt;
    }
    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        categories.forEach(value -> ctgHash.add(value.id));
    }

    public HashSet getCtgHash() {
        return ctgHash;
    }
    

    public Product() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setPrice(Float price) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Product(int id) {
        this.id = id;
    }

    //SELECT PRODUCTS.ID, PRODUCTS.TITLE, PRODUCTS.PRICE, PRODUCTS.STOCK, PRODUCTS.SKU, PRODUCTS.MANUFACTURER, PRODUCTS.PUBLISHEDAT, PRODUCTS.ACTIVE, IMG.IMGPATH
    public Product(int id, String title, Float price, int stock, String sku, String manufacturer, Date publishedAt, boolean active,String productImg) {
        this.id = id;
        this.title = title;
        this.stock = stock;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
        this.sku = sku;
        this.manufacturer = manufacturer;
        this.publishedAt = publishedAt;
        this.active = active;
        this.productImg = productImg;
    }

    //PRODUCTS.ID, PRODUCTS.TITLE, PRODUCTS.SKU, PRODUCTS.MANUFACTURER, PRODUCT_TYPE.TITLE AS TYPE, PRODUCTS.STOCK, IMG.IMGPATH
    public Product(int id, String title, String sku, String manufacturer, String type, int stock, String productImg) {
        this.id = id;
        this.title = title;
        this.sku = sku;
        this.manufacturer = manufacturer;
        this.type = new ProductType(type);
        this.stock = stock;
        this.productImg = productImg;
    }

    public Product(String title, String slug, String description, ProductType type, String sku, Float price, int stock) {
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.sku = sku;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
        this.stock = stock;
        this.type = type;
    }

    public Product(int id, String title, String slug, String description, ProductType type, String sku, Float price, int stock) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.type = type;
        this.sku = sku;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
        this.stock = stock;
    }

    //RODUCTS.TITLE, PRODUCTS.PRICE, PRODUCTS.SLUG, IMG.IMGPATH
    public Product(String title, Float price, String slug, String productImg) {
        this.title = title;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
        this.slug = slug;
        this.productImg = productImg;
    }

    public Product(int id, String title, String slug, String description, ProductType type, String sku, Float price, int stock, String manufacturer, Date createdAt, Date updatedAt, Date publishedAt, boolean active, String productImg, List<ProductMeta> meta, List<Category> categories) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.type = type;
        this.sku = sku;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
        this.stock = stock;
        this.manufacturer = manufacturer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.publishedAt = publishedAt;
        this.active = active;
        this.productImg = productImg;
        this.meta = meta;
        this.categories = categories;
        categories.forEach(value -> ctgHash.add(value.id));
    }

    //PRODUCTS.ID, PRODUCTS.TITLE, PRODUCTS.SLUG, PRODUCTS.DESCRIPTION, PRODUCT_TYPE.TITLE AS TYPE, PRODUCTS.SKU, PRODUCTS.PRICE, PRODUCTS.STOCK, PRODUCTS.MANUFACTURER, PRODUCTS.ACTIVE
    public Product(int id, String title, String slug, String description, String type, String sku, Float price, int stock, String manufacturer, boolean active) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.type = new ProductType(type);
        this.sku = sku;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
        this.stock = stock;
        this.manufacturer = manufacturer;
        this.active = active;
    }

    //PRODUCTS.TITLE, PRODUCT_TYPE.TITLE AS TYPE, PRICE, STOCK, MANUFACTURER, SKU, ACTIVE
    public Product(String title, String type, Float price, int stock, String manufacturer, String sku, boolean active) {
        this.title = title;
        this.type = new ProductType(type);
        this.sku = sku;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
        this.stock = stock;
        this.manufacturer = manufacturer;
        this.sku = sku;
        this.active = active;
    }

    //ORDER_ITEM.ID, PRODUCTS.TITLE, ORDER_ITEM.QUANTITY, ORDER_ITEM.PRICE, PRODUCTS.SLUG, IMG.IMGPATH
    public Product(int id, String title, int quantity, Float price, String slug, String productImg) {
        this.id = id;
        this.title = title;
        this.stock = quantity;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
        this.slug = slug;
        this.productImg = productImg;
    }

    public Product(String title, int quantity, Float price) {
        this.title = title;
        this.stock = quantity;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
    }
    
    //PRODUCTS.TITLE, ORDER_ITEM.QUANTITY, ORDER_ITEM.PRICE AS TOTAL, PRODUCTS.SKU, PRODUCTS.PRICE AS UNIT_PRICE FROM ORDER_ITEM
    public Product(String title, int quantity, Float price, String sku){
        this.title = title;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
        this.sku = sku;
        this.stock = quantity;
    }
    
    //MIN(PRODUCTID) AS PRODUCTID, MIN(PRODUCTS.TITLE) AS TITLE, MIN(PRODUCTS.SKU) AS SKU, MIN(PRODUCTS.ACTIVE) AS ACTIVE, SUM(ORDER_ITEM.PRICE) AS TOTAL_SALES, SUM(ORDER_ITEM.QUANTITY) AS QTY_SOLD
    public Product(int id, String title, String sku, boolean active, Float total_sales, int qty_sold){
        this.id = id;
        this.title = title;
        this.sku = sku;
        this.active = active;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(total_sales));
        this.stock = qty_sold;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setMeta(List<ProductMeta> meta) {
        this.meta = meta;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type.title;
    }
    
    public ProductType getProductType() {
        return type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getSku() {
        return sku;
    }

    public Float getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public boolean isActive() {
        return active;
    }


    public List<ProductMeta> getMeta() {
        return meta;
    }

}
