package models.entity;

public class ProductMeta{
    Integer id;
    String key;
    String value;

    public ProductMeta(int id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public ProductMeta(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    
    
}