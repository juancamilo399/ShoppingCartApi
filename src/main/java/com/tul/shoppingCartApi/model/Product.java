package com.tul.shoppingCartApi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @Column(name = "id_product")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String  id;
    private String name;
    private String description;
    private String sku;
    private Double price;
    private String type;
    public Product() { }
    public String getId() {

        return id;
    }
    public void setId(String  id) {
        this.id = id;
    }
    public String getName() {

        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {

        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSku() {

        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public Double getPrice() {

        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getType() {

        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    private static final long serialVersionUID = 1L;
}
