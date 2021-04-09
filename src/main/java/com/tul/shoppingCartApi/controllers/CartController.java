package com.tul.shoppingCartApi.controllers;

import com.tul.shoppingCartApi.exceptions.CartNotFoundException;
import com.tul.shoppingCartApi.exceptions.ProductNotInCartException;
import com.tul.shoppingCartApi.services.CartServices;
import com.tul.shoppingCartApi.controllers.requests.ShoppingProductRequest;
import com.tul.shoppingCartApi.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    @Autowired
    CartServices cartServices;
    @GetMapping("/{id}/products")
    public ResponseEntity<?> getProductsOfCart(@PathVariable String id) {
        try {

            return new ResponseEntity<>(cartServices.getProductsOfCart(id), HttpStatus.OK);
        } catch (CartNotFoundException cartNotFoundException) {

            return new ResponseEntity<>(cartNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping()
    public ResponseEntity<?> getCarts() {

        return new ResponseEntity<>(cartServices.getCarts(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createCart() {

        return new ResponseEntity<>(cartServices.createCart(), HttpStatus.CREATED);
    }
    @PostMapping("{idCart}/products")
    public ResponseEntity<?> addProductToCart(@PathVariable String idCart, @RequestBody ShoppingProductRequest request) {
        try {

            return new ResponseEntity<>(cartServices.addProductToCart(idCart, request.getProductId(), request.getQuantity()), HttpStatus.CREATED);
        } catch (CartNotFoundException cartNotFoundException) {

            return new ResponseEntity<>(cartNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ProductNotFoundException productNotFoundException) {

            return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("{idCart}/products")
    public ResponseEntity<?> updateProductOfCart(@PathVariable String idCart, @RequestBody ShoppingProductRequest request) {
        try {

            return new ResponseEntity<>(cartServices.updateProductOfCart(idCart, request.getProductId(), request.getQuantity()), HttpStatus.OK);
        } catch (CartNotFoundException cartNotFoundException) {

            return new ResponseEntity<>(cartNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ProductNotFoundException productNotFoundException) {

            return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ProductNotInCartException productNotInCartException) {

            return new ResponseEntity<>(productNotInCartException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{idCart}/products/{idProduct}")
    public ResponseEntity<?> deleteProductFromCart(@PathVariable String idCart, @PathVariable String idProduct) {
        try {
            cartServices.deleteProductFromCart(idCart, idProduct);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CartNotFoundException cartNotFoundException) {

            return new ResponseEntity<>(cartNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ProductNotFoundException productNotFoundException) {

            return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ProductNotInCartException productNotInCartException) {

            return new ResponseEntity<>(productNotInCartException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{idCart}/checkout")
    public ResponseEntity<?> deleteProductFromCart(@PathVariable String idCart) {
        Double total = null;
        try {
            total = cartServices.checkoutCart(idCart);

            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (CartNotFoundException cartNotFoundException) {

            return new ResponseEntity<>(cartNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}