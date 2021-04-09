package com.tul.shoppingCartApi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shopingProducts")
public class ShoppingProduct implements Serializable {
    @Id
    @Column(name = "id_shopping")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Cart cart;
    private Integer quantity;
    public ShoppingProduct() { }
    public String getId() {

        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Product getProduct() {

        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Cart getCart() {

        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public Integer getQuantity() {

        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    private static final long serialVersionUID = 1L;
}
