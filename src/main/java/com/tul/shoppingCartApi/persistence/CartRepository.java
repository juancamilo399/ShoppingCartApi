package com.tul.shoppingCartApi.persistence;

import com.tul.shoppingCartApi.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, String> {
}
