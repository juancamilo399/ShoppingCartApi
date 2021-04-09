package com.tul.shoppingCartApi.controllers.requests;

public class ShoppingProductRequest {
    private String productId;
    private Integer quantity;
    public ShoppingProductRequest() {}
    public String getProductId() { return productId; }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
