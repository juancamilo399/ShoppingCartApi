package com.tul.shoppingCartApi.services.Impl;

import com.tul.shoppingCartApi.exceptions.CartNotFoundException;
import com.tul.shoppingCartApi.exceptions.ProductNotInCartException;
import com.tul.shoppingCartApi.exceptions.ProductNotFoundException;
import com.tul.shoppingCartApi.model.Cart;
import com.tul.shoppingCartApi.model.Product;
import com.tul.shoppingCartApi.model.ShoppingProduct;
import com.tul.shoppingCartApi.model.enums.Status;
import com.tul.shoppingCartApi.persistence.CartRepository;
import com.tul.shoppingCartApi.persistence.ProductRepository;
import com.tul.shoppingCartApi.persistence.ShoppingProductRepository;
import com.tul.shoppingCartApi.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServicesImpl implements CartServices {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShoppingProductRepository shoppingProductRepository;
    @Override
    public Cart createCart() {
        return cartRepository.save(new Cart());
    }
    @Override
    public List<ShoppingProduct> getProductsOfCart(String id) throws CartNotFoundException {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(id));

        return cart.getProducts();
    }
    @Override
    public ShoppingProduct addProductToCart(String cartId, String productId, Integer quantity) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        Optional<ShoppingProduct> optionalShoppingProduct = cart.getProducts().stream().filter(p -> p.getProduct().equals(product)).findAny();
        if (optionalShoppingProduct.isPresent()) {
            cart.getProducts().forEach(p -> {
                if (p.equals(optionalShoppingProduct.get())) {
                    p.setQuantity(p.getQuantity() + quantity);
                    cartRepository.save(cart);
                }
            });

            return optionalShoppingProduct.get();
        } else {
            ShoppingProduct shoppingProduct = new ShoppingProduct();
            shoppingProduct.setProduct(product);
            shoppingProduct.setQuantity(quantity);
            shoppingProduct.setCart(cart);
            cartRepository.save(cart);

            return shoppingProductRepository.save(shoppingProduct);
        }
    }
    @Override
    public ShoppingProduct updateProductOfCart(String cartId, String productId, Integer quantity) throws CartNotFoundException, ProductNotFoundException, ProductNotInCartException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        Optional<ShoppingProduct> shoppingProduct = cart.getProducts().stream().filter(p -> p.getProduct().equals(product)).findAny();
        if (shoppingProduct.isPresent()) {
            cart.getProducts().forEach(p -> {
                if (p.equals(shoppingProduct.get())) {
                    p.setQuantity(quantity);
                    cartRepository.save(cart);
                }
            });

            return shoppingProduct.get();
        }
        throw new ProductNotInCartException(productId);
    }
    @Override
    public void deleteProductFromCart(String cartId, String productId) throws CartNotFoundException, ProductNotFoundException, ProductNotInCartException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        Optional<ShoppingProduct> shoppingProduct = cart.getProducts().stream().filter(p -> p.getProduct().equals(product)).findAny();
        if (shoppingProduct.isPresent()) {
            cart.getProducts().remove(shoppingProduct.get());
            cartRepository.save(cart);
            shoppingProductRepository.deleteById(shoppingProduct.get().getId());
        }
        throw new ProductNotInCartException(productId);
    }
    @Override
    public Double checkoutCart(String cartId) throws CartNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        Double total = cart.getProducts().stream().map(this::calculatePrice).mapToDouble(Double::doubleValue).sum();
        cart.setStatus(Status.Completed);
        cartRepository.save(cart);

        return total;
    }
    @Override
    public List<Cart> getCarts() {
        List<Cart> cartList = new ArrayList<>();
        cartRepository.findAll().forEach(cartList::add);

        return cartList;
    }
    private Double calculatePrice(ShoppingProduct shoppingProduct) {
        Double price = shoppingProduct.getProduct().getPrice();
        if (shoppingProduct.getProduct().getType().equals("Descuento")) {
            price /= 2;
        }

        return price * shoppingProduct.getQuantity();
    }
}