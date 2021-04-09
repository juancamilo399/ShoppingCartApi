package com.tul.shoppingCartApi.services;

import com.tul.shoppingCartApi.exceptions.ProductNotFoundException;
import com.tul.shoppingCartApi.model.Product;

import java.util.List;

public interface ProductServices {
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product updateProduct(Product product) throws ProductNotFoundException;
    void deleteProduct(String id) throws ProductNotFoundException;
}