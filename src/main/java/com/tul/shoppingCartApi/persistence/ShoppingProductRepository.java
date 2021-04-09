package com.tul.shoppingCartApi.persistence;

import com.tul.shoppingCartApi.model.ShoppingProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingProductRepository extends CrudRepository<ShoppingProduct, String> {
}
