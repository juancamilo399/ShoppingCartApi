package com.tul.shoppingCartApi.persistence;

import com.tul.shoppingCartApi.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
}
