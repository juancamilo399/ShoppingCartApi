package com.tul.shoppingCartApi.controllers;

import com.tul.shoppingCartApi.services.ProductServices;
import com.tul.shoppingCartApi.exceptions.ProductNotFoundException;
import com.tul.shoppingCartApi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    ProductServices productServices;
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {

        return new ResponseEntity<>(productServices.createProduct(product), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> getProducts() {

        return new ResponseEntity<>(productServices.getAllProducts(), HttpStatus.OK);
    }
    @DeleteMapping("/idproduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        try {
            productServices.deleteProduct(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProductNotFoundException productNotFoundException) {

            return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {

            return new ResponseEntity<>(productServices.updateProduct(product), HttpStatus.OK);
        } catch (ProductNotFoundException productNotFoundException) {

            return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}