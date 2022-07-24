package models.entity;

public class ProductType{
    Integer id;
    String title;

    public ProductType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public ProductType(int id, String title) {
        this.id = id;
        this.title = title;
    }
    
    public ProductType(String title){
        this.title = title;
    }

    public ProductType(Integer id) {
        this.id = id;
    }
    
    
}