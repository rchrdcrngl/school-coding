package models.dao;

import com.itextpdf.text.DocumentException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.entity.Category;
import models.entity.Product;
import models.entity.ProductMeta;
import models.exception.DatabaseException;
import models.util.DatabaseHelper;


public class ProductDAO{
    DatabaseHelper db;
    
    public ProductDAO(){
        db = new DatabaseHelper();
    }
    
    public ProductDAO(Connection con){
        db = new DatabaseHelper();
        DatabaseHelper.setCon(con);
    }
    public List<Product> getTopProducts() throws DatabaseException{
        List<Product> list = new ArrayList<Product>();
        try{
            String qString = "SELECT PRODUCTS.TITLE, PRODUCTS.PRICE, PRODUCTS.SLUG, PRODUCTS.IMG FROM PRODUCTS INNER JOIN PRODUCT_TYPE ON PRODUCT_TYPE.ID = PRODUCTS.PRODUCTTYPE WHERE ACTIVE = TRUE FETCH FIRST 10 ROWS ONLY";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new Product(
                   rs.getString("TITLE"), rs.getFloat("PRICE"), rs.getString("SLUG"), rs.getString("IMG")
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public List<Product> getAllProductsByType(int productType) throws DatabaseException{
        List<Product> list = new ArrayList<Product>();
        try{
            String qString = "SELECT PRODUCTS.TITLE, PRODUCTS.PRICE, PRODUCTS.SLUG, PRODUCTS.IMG FROM PRODUCTS INNER JOIN PRODUCT_TYPE ON PRODUCT_TYPE.ID = PRODUCTS.PRODUCTTYPE  WHERE ACTIVE = TRUE AND PRODUCTTYPE = ?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, productType);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new Product(
                   rs.getString("TITLE"), rs.getFloat("PRICE"), rs.getString("SLUG"), rs.getString("IMG")
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public List<Product> getAllProductsByCategory(String categorySlug) throws DatabaseException{
        List<Product> list = new ArrayList<Product>();
        try{
            String qString = "SELECT PRODUCTS.TITLE, PRODUCTS.PRICE, PRODUCTS.SLUG, PRODUCTS.IMG FROM PRODUCTS INNER JOIN PRODUCT_CATEGORY ON PRODUCT_CATEGORY.PRODUCTID = PRODUCTS.ID INNER JOIN CATEGORY ON PRODUCT_CATEGORY.CATEGORYID = CATEGORY.ID  WHERE ACTIVE = TRUE AND CATEGORY.SLUG = ?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setString(1, categorySlug);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new Product(
                   rs.getString("TITLE"), rs.getFloat("PRICE"), rs.getString("SLUG"), rs.getString("IMG")
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public List<Product> searchProduct(String strSearch) throws DatabaseException{
        List<Product> list = new ArrayList<Product>();
        try{
            String qString = "SELECT PRODUCTS.TITLE, PRODUCTS.PRICE, PRODUCTS.SLUG, PRODUCTS.IMG FROM PRODUCTS  WHERE ACTIVE = TRUE AND UPPER(PRODUCTS.TITLE) LIKE ?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setString(1, "%"+strSearch.toUpperCase()+"%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new Product(
                   rs.getString("TITLE"), rs.getFloat("PRICE"), rs.getString("SLUG"),rs.getString("IMG")
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public List<Product> searchProductByType(int productType, String strSearch) throws DatabaseException{
        List<Product> list = new ArrayList<Product>();
        try{
            String qString = "SELECT PRODUCTS.TITLE, PRODUCTS.PRICE, PRODUCTS.SLUG, PRODUCTS.IMG FROM PRODUCTS INNER JOIN PRODUCT_TYPE ON PRODUCT_TYPE.ID = PRODUCTS.PRODUCTTYPE  WHERE ACTIVE = TRUE AND PRODUCTTYPE = ? AND UPPER(PRODUCTS.TITLE) LIKE ?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, productType);
            ps.setString(2, "%"+strSearch.toUpperCase()+"%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new Product(
                   rs.getString("TITLE"), rs.getFloat("PRICE"), rs.getString("SLUG"), rs.getString("IMG")
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }

    public Product userGetProduct(String productSlug) throws DatabaseException, NullPointerException{
        List<ProductMeta> meta_list = new ArrayList<ProductMeta>();
        List<Category> category_list = new ArrayList<Category>();
        Product product = null;
        try{
            //Product Info
            String qString = "SELECT PRODUCTS.ID, PRODUCTS.TITLE, PRODUCTS.SLUG, PRODUCTS.DESCRIPTION, PRODUCT_TYPE.TITLE AS TYPE, PRODUCTS.SKU, PRODUCTS.PRICE, PRODUCTS.STOCK, PRODUCTS.MANUFACTURER, PRODUCTS.ACTIVE, PRODUCTS.IMG FROM PRODUCTS INNER JOIN PRODUCT_TYPE ON PRODUCT_TYPE.ID = PRODUCTS.PRODUCTTYPE WHERE PRODUCTS.SLUG=?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setString(1, productSlug);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                product = new Product(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("SLUG"), rs.getString("DESCRIPTION"), rs.getString("TYPE"), rs.getString("SKU"), rs.getFloat("PRICE"), rs.getInt("STOCK"), rs.getString("MANUFACTURER"), rs.getBoolean("ACTIVE"));
                product.setProductImg(rs.getString("IMG"));
            }
            rs.close();
            ps.close();
            //Product Meta
            String qString2 = "SELECT PRODUCT_META.META_KEY, PRODUCT_META.META_VALUE FROM PRODUCT_META WHERE PRODUCT_META.PRODUCTID = (SELECT ID FROM PRODUCTS WHERE SLUG=?)";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString2);
            ps2.setString(1, productSlug);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                meta_list.add(new ProductMeta(rs2.getString("META_KEY"), rs2.getString("META_VALUE")));
            }
            rs2.close();
            ps2.close();
            //Product Category
            String qString3 = "SELECT CATEGORY.TITLE, CATEGORY.SLUG FROM PRODUCT_CATEGORY INNER JOIN CATEGORY ON PRODUCT_CATEGORY.CATEGORYID = CATEGORY.ID WHERE PRODUCT_CATEGORY.PRODUCTID = (SELECT ID FROM PRODUCTS WHERE SLUG=?)";
            PreparedStatement ps3 = db.getCon().prepareStatement(qString3);
            ps3.setString(1, productSlug);
            ResultSet rs3 = ps3.executeQuery();
            while(rs3.next()){
                category_list.add(new Category(rs3.getString("TITLE"), rs3.getString("SLUG")));
            }
            rs3.close();
            ps3.close();
            product.setMeta(meta_list);
            product.setCategories(category_list);
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } catch( NullPointerException e){
            throw e;
        }
        return product;
    }
    
    public List<Product> getStockList() throws DatabaseException{
        List<Product> list = new ArrayList<Product>();
        try{
            String qString = "SELECT PRODUCTS.ID, PRODUCTS.TITLE, PRODUCTS.SKU, PRODUCTS.MANUFACTURER, PRODUCT_TYPE.TITLE AS TYPE, PRODUCTS.STOCK, PRODUCTS.IMG FROM PRODUCTS INNER JOIN PRODUCT_TYPE ON PRODUCT_TYPE.ID = PRODUCTS.PRODUCTTYPE  WHERE ACTIVE = TRUE";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new Product(
                   rs.getInt("ID"), rs.getString("TITLE"), rs.getString("SKU"), rs.getString("MANUFACTURER"), rs.getString("TYPE"), rs.getInt("STOCK"), rs.getString("IMG")
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public int updateProductStock(int id, int stock) throws DatabaseException{
        int success=-1;
        try {
            String qString = "UPDATE PRODUCTS SET STOCK = ?, UPDATEDAT = CURRENT TIMESTAMP WHERE ID = ?";
            //Add data to database
            PreparedStatement ps2 = db.getCon().prepareStatement(qString);
            ps2.setInt(1, stock);
            ps2.setInt(2, id);
            ps2.execute();
            success = 1;
            ps2.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    } 

    public List<Product> getProductList() throws DatabaseException{
        List<Product> list = new ArrayList<Product>();
        try{
            String qString = "SELECT PRODUCTS.ID, PRODUCTS.TITLE, PRODUCTS.PRICE, PRODUCTS.STOCK, PRODUCTS.SKU, PRODUCTS.MANUFACTURER, PRODUCTS.PUBLISHEDAT, PRODUCTS.ACTIVE, PRODUCTS.IMG FROM PRODUCTS";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new Product(
                   rs.getInt("ID"), rs.getString("TITLE"), rs.getFloat("PRICE"), rs.getInt("STOCK"), rs.getString("SKU"), rs.getString("MANUFACTURER"), rs.getDate("PUBLISHEDAT"), rs.getBoolean("ACTIVE"),rs.getString("IMG")
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }

    public Product adminGetProduct(int productId) throws DatabaseException, NullPointerException{
        List<ProductMeta> meta_list = new ArrayList<ProductMeta>();
        List<Category> category_list = new ArrayList<Category>();
        Product product = null;
        try{
            //Product Info
            String qString = "SELECT PRODUCTS.*, PRODUCT_TYPE.ID AS TYPE_ID, PRODUCT_TYPE.TITLE AS PTYPE FROM PRODUCTS INNER JOIN PRODUCT_TYPE ON PRODUCT_TYPE.ID = PRODUCTS.PRODUCTTYPE WHERE PRODUCTS.ID=?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                product = new Product(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("SLUG"), rs.getString("DESCRIPTION"), rs.getString("PTYPE"), rs.getString("SKU"), rs.getFloat("PRICE"), rs.getInt("STOCK"), rs.getString("MANUFACTURER"), rs.getBoolean("ACTIVE"));
                product.setProductImg(rs.getString("IMG"));
                product.getProductType().setId(rs.getInt("TYPE_ID"));
            }
            rs.close();
            ps.close();
            //Product Meta
            String qString2 = "SELECT PRODUCT_META.ID, PRODUCT_META.META_KEY, PRODUCT_META.META_VALUE FROM PRODUCT_META WHERE PRODUCT_META.PRODUCTID = ?";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString2);
            ps2.setInt(1, productId);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                meta_list.add(new ProductMeta(rs2.getInt("ID"), rs2.getString("META_KEY"), rs2.getString("META_VALUE")));
            }
            rs2.close();
            ps2.close();
            //Product Category
            String qString3 = "SELECT CATEGORY.ID, CATEGORY.TITLE, CATEGORY.SLUG FROM PRODUCT_CATEGORY INNER JOIN CATEGORY ON PRODUCT_CATEGORY.CATEGORYID = CATEGORY.ID WHERE PRODUCT_CATEGORY.PRODUCTID = ?";
            PreparedStatement ps3 = db.getCon().prepareStatement(qString3);
            ps3.setInt(1, productId);
            ResultSet rs3 = ps3.executeQuery();
            while(rs3.next()){
                category_list.add(new Category(rs3.getInt("ID"), rs3.getString("TITLE"), rs3.getString("SLUG")));
            }
            rs3.close();
            ps3.close();
            product.setMeta(meta_list);
            product.setCategories(category_list);
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } catch( NullPointerException e){
            throw e;
        }
        return product;
    }

    public int addProduct(Product product) throws DatabaseException{
        int success=-1;
        try {
            //Add Product Info
            String qString = "INSERT INTO PRODUCTS (TITLE, SLUG, DESCRIPTION, PRODUCTTYPE, SKU, PRICE, STOCK, MANUFACTURER, IMG, ACTIVE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, true)";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getSlug());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getProductType().getId());
            ps.setString(5, product.getSku());
            ps.setFloat(6, product.getPrice());
            ps.setInt(7, product.getStock());
            ps.setString(8, product.getManufacturer());
            ps.setString(9, product.getProductImg());
            ps.execute();
            ps.close();
           
            //Add Meta
            for(ProductMeta meta: product.getMeta()){
                String qString3 = "INSERT INTO PRODUCT_META (PRODUCTID, META_KEY, META_VALUE) VALUES ((SELECT ID FROM PRODUCTS WHERE TITLE=?),?,?)";
                PreparedStatement ps3 = db.getCon().prepareStatement(qString3);
                ps3.setString(1, product.getTitle());
                ps3.setString(2, meta.getKey());
                ps3.setString(3, meta.getValue());
                ps3.execute();
                ps3.close();
            }
            //Add Category
            for(Category ctg: product.getCategories()){
                String qString4 = "INSERT INTO PRODUCT_CATEGORY (PRODUCTID, CATEGORYID) VALUES ((SELECT ID FROM PRODUCTS WHERE TITLE=?),?)";
                PreparedStatement ps4 = db.getCon().prepareStatement(qString4);
                ps4.setString(1, product.getTitle());
                ps4.setInt(2, ctg.getId());
                ps4.execute();
                ps4.close();
            }
            success = 1;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }
    
    public int editProduct(Product product) throws DatabaseException{
        int success=-1;
        try {
            //Edit Product Info
            String qString = "UPDATE PRODUCTS SET TITLE=?, SLUG=?, DESCRIPTION=?, PRODUCTTYPE=?, SKU=?, PRICE=?, STOCK=?, MANUFACTURER=?, IMG=?, ACTIVE=?, UPDATEDAT = CURRENT TIMESTAMP WHERE ID = ?";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getSlug());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getProductType().getId());
            ps.setString(5, product.getSku());
            ps.setFloat(6, product.getPrice());
            ps.setInt(7, product.getStock());
            ps.setString(8, product.getManufacturer());
            ps.setString(9, product.getProductImg());
            ps.setBoolean(10, product.isActive());
            ps.setInt(11, product.getId());
            ps.execute();
            ps.close();
            deleteProductMeta(product.getId());
            //DEdit Meta
            for(ProductMeta meta: product.getMeta()){
                String qString4 = "INSERT INTO PRODUCT_META (PRODUCTID, META_KEY, META_VALUE) VALUES (?,?,?)";
                PreparedStatement ps4 = db.getCon().prepareStatement(qString4);
                ps4.setInt(1, product.getId());
                ps4.setString(2, meta.getKey());
                ps4.setString(3, meta.getValue());
                ps4.execute();
                ps4.close();
            }
            deleteProductCategory(product.getId());
            //Add Category
            for(Category ctg: product.getCategories()){
                String qString4 = "INSERT INTO PRODUCT_CATEGORY (PRODUCTID, CATEGORYID) VALUES ((SELECT ID FROM PRODUCTS WHERE TITLE=?),?)";
                PreparedStatement ps4 = db.getCon().prepareStatement(qString4);
                ps4.setString(1, product.getTitle());
                ps4.setInt(2, ctg.getId());
                ps4.execute();
                ps4.close();
            }
            success = 1;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }
    
    public int deleteProduct(int pid) throws DatabaseException{
        int success=-1;
        try {
            deleteProductMeta(pid);
            deleteProductCategory(pid);
            String qString = "DELETE FROM PRODUCTS WHERE ID=?";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString);
            ps2.setInt(1, pid);
            ps2.execute();
            success = 1;
            ps2.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }
    
    public int deleteProductMeta(int pid) throws DatabaseException{
        int success=-1;
        try {
            String qString = "DELETE FROM PRODUCT_META WHERE PRODUCTID=?";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString);
            ps2.setInt(1, pid);
            ps2.execute();
            success = 1;
            ps2.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }

    public int deleteProductCategory(int productId, int categoryId) throws DatabaseException{
        int success=-1;
        try {
            String qString = "DELETE FROM PRODUCT_CATEGORY WHERE PRODUCTID=? AND CATEGORYID=?";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString);
            ps2.setInt(1, productId);
            ps2.setInt(2, categoryId);
            ps2.execute();
            success = 1;
            ps2.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }
    
    public int deleteProductCategory(int productId) throws DatabaseException{
        int success=-1;
        try {
            String qString = "DELETE FROM PRODUCT_CATEGORY WHERE PRODUCTID=?";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString);
            ps2.setInt(1, productId);
            ps2.execute();
            success = 1;
            ps2.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }
    
    public int hideProduct(int productId) throws DatabaseException{
        int success=-1;
        try {
            String qString = "UPDATE PRODUCTS SET ACTIVE=FALSE, UPDATEDAT = CURRENT TIMESTAMP WHERE ID = ?";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString);
            ps2.setInt(1, productId);
            ps2.execute();
            success = 1;
            ps2.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }
    
    public int showProduct(int productId) throws DatabaseException{
        int success=-1;
        try {
            String qString = "UPDATE PRODUCTS SET ACTIVE=FALSE, PUBLISHEDAT = CURRENT TIMESTAMP WHERE ID = ?";
            PreparedStatement ps2 = db.getCon().prepareStatement(qString);
            ps2.setInt(1, productId);
            ps2.execute();
            success = 1;
            ps2.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return success;
    }
    
    public List<Product> getSalesReportData() throws DatabaseException, DocumentException{
        List<Product> list = new ArrayList<Product>();
        try{
            String qString = "SELECT MIN(PRODUCTID) AS PRODUCTID, MIN(PRODUCTS.TITLE) AS TITLE, MIN(PRODUCTS.SKU) AS SKU, MIN(PRODUCTS.ACTIVE) AS ACTIVE, SUM(ORDER_ITEM.PRICE) AS TOTAL_SALES, SUM(ORDER_ITEM.QUANTITY) AS QTY_SOLD FROM ORDER_ITEM INNER JOIN PRODUCTS ON PRODUCTS.ID = ORDER_ITEM.PRODUCTID INNER JOIN ORDERS ON ORDER_ITEM.ORDERID = ORDERS.ID WHERE ORDERS.STATUS >0 GROUP BY PRODUCTID ORDER BY TOTAL_SALES DESC";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new Product(
                   rs.getInt("PRODUCTID"), rs.getString("TITLE"), rs.getString("SKU"), rs.getBoolean("ACTIVE"), rs.getFloat("TOTAL_SALES"), rs.getInt("QTY_SOLD")
                ));
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return list;
    }
    
    public List<Product> getProductListReportData() throws DatabaseException, DocumentException{
        List<Product> list = new ArrayList<Product>();
        try{
            String qString = "SELECT PRODUCTS.TITLE, PRODUCT_TYPE.TITLE AS TYPE, PRICE, STOCK, MANUFACTURER, SKU, ACTIVE FROM PRODUCTS INNER JOIN PRODUCT_TYPE ON PRODUCT_TYPE.ID = PRODUCTS.PRODUCTTYPE ORDER BY ACTIVE DESC";
            PreparedStatement ps = db.getCon().prepareStatement(qString);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new Product(
                   rs.getString("TITLE"), rs.getString("TYPE"), rs.getFloat("PRICE"), rs.getInt("STOCK"), rs.getString("MANUFACTURER"), rs.getString("SKU"), rs.getBoolean("ACTIVE")
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