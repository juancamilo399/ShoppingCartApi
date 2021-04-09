package com.tul.shoppingCartApi.exceptions;

public class ProductNotFoundException extends Exception {
    public static final String NOT_FOUND = "Product not found: ";
    public ProductNotFoundException() {
        super();
    }
    public ProductNotFoundException(String message) {
        super(NOT_FOUND + message);
    }
}