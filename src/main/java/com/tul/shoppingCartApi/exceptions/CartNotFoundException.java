package com.tul.shoppingCartApi.exceptions;

public class CartNotFoundException extends Exception {
    public static final String NOT_FOUND = "Cart not found: ";
    public CartNotFoundException() {
        super();
    }
    public CartNotFoundException(String message) {
        super(NOT_FOUND + message);
    }
}