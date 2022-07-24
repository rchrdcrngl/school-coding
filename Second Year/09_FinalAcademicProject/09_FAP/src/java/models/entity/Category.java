package models.entity;

import java.sql.Date;

public class Category{
    Integer id;
    String title;
    String slug;
    Integer parentId;

    public Category(String title, String slug) {
        this.title = title;
        this.slug = slug;
    }

    public Category(Integer id) {
        this.id = id;
    }
    

    public void setId(Integer id) {
        this.id = id;
    }

    public Category() {
    }

    public Category(int id, String title, String slug, int parentId) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.parentId = parentId;
    }
    
    public Category(int id, String title, String slug) {
        this.id = id;
        this.title = title;
        this.slug = slug;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public int getParentId() {
        return parentId;
    }
    
    
}