package com.tul.shoppingCartApi.services.Impl;

import com.tul.shoppingCartApi.services.ProductServices;
import com.tul.shoppingCartApi.exceptions.ProductNotFoundException;
import com.tul.shoppingCartApi.model.Product;
import com.tul.shoppingCartApi.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServicesImpl implements ProductServices {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);

        return products;
    }
    @Override
    public Product createProduct(Product product) {

        return productRepository.save(product);
    }
    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {
        if (!productRepository.existsById(product.getId())) {
            throw new ProductNotFoundException(product.getId());
        }

        return productRepository.save(product);
    }
    @Override
    public void deleteProduct(String id) throws ProductNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}