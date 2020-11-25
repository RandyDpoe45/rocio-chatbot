package com.wesdom.rocio.services;

import com.wesdom.rocio.model.Product;

public interface ProductService {

    Product create(Product product);
    Product update(Long id, Product product);
}
