package com.tul.shoppingCartApi.services;

import com.tul.shoppingCartApi.exceptions.CartNotFoundException;
import com.tul.shoppingCartApi.exceptions.ProductNotInCartException;
import com.tul.shoppingCartApi.exceptions.ProductNotFoundException;
import com.tul.shoppingCartApi.model.Cart;
import com.tul.shoppingCartApi.model.ShoppingProduct;

import java.util.List;

public interface CartServices {
    Cart createCart();
    List<ShoppingProduct> getProductsOfCart (String id) throws CartNotFoundException;
    List<Cart> getCarts();
    ShoppingProduct addProductToCart(String cartId, String productId, Integer quantity) throws CartNotFoundException, ProductNotFoundException;
    ShoppingProduct updateProductOfCart(String cartId, String productId, Integer quantity) throws CartNotFoundException, ProductNotFoundException, ProductNotInCartException;
    void deleteProductFromCart(String cartId, String productId) throws CartNotFoundException, ProductNotFoundException, ProductNotInCartException;
    Double checkoutCart(String cartId) throws CartNotFoundException;
}