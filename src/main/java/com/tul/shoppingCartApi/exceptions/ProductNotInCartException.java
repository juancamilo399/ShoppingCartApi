package com.tul.shoppingCartApi.exceptions;

public class ProductNotInCartException extends Exception {
    public static final String NOT_FOUND = "Product not in cart: ";
    public ProductNotInCartException() {
        super();
    }
    public ProductNotInCartException(String message) {
        super(NOT_FOUND + message);
    }
}