package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.database.repositories.ProductRepository;
import com.wesdom.rocio.model.Product;
import com.wesdom.rocio.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.create(product);
    }

    @Override
    public Product update(Long id, Product product) {
        return productRepository.update(id,product);
    }
}
