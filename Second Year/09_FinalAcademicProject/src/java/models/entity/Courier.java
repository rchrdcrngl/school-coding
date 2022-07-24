package models.entity;

import java.text.DecimalFormat;


public class Courier{
    Integer id;
    String courierName;
    String description;
    Float price;

    public Courier(String courierName, String description, Float price) {
        this.courierName = courierName;
        this.description = description;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
    }

    public Courier(int id, String courierName, String description, Float price) {
        this.id = id;
        this.courierName = courierName;
        this.description = description;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
    }
    
    public Courier(int id, String courierName, Float price) {
        this.id = id;
        this.courierName = courierName;
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = Float.valueOf(df.format(price));
    }
    
    public Courier(int id, String courierName) {
        this.id = id;
        this.courierName = courierName;
    }

    public int getId() {
        return id;
    }

    public String getCourierName() {
        return courierName;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public Courier(int id) {
        this.id = id;
    }
    
}