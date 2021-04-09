package com.tul.shoppingCartApi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tul.shoppingCartApi.model.enums.Status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @Column(name = "id_cart")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Status status;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ShoppingProduct> products;
    public Cart() {
        this.setStatus(Status.Pending);
        this.setProducts(new ArrayList<>());
    }
    public String getId() {

        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Status getStatus() {

        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public List<ShoppingProduct> getProducts() {

        return products;
    }
    public void setProducts(List<ShoppingProduct> products) {
        this.products = products;
    }
}