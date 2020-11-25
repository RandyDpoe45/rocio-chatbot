/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.ProductJpaRepository;
import com.wesdom.rocio.database.repositories.ProductRepository;
import com.wesdom.rocio.model.Product;
import com.wesdom.rocio.services.IPaginationBuilder;
import com.wesdom.rocio.services.IPredicateBuilder;
import com.wesdom.rocio.servicesimpl.PaginationBuilderImpl;
import com.wesdom.rocio.servicesimpl.PredicateBuilderServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author randy
 */
@Service
public class ProductRepositoryImpl implements ProductRepository{

    @Autowired
    private ProductJpaRepository productJpaRepository;
    
    @Override
    public Page<Product> getAll(Map<String, String> queryParams) {
        IPredicateBuilder<Product> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder pagination = new PaginationBuilderImpl();
        if(queryParams == null){
            queryParams = new HashMap<>();
        }
        return productJpaRepository.findAll(predicate.createPredicate(queryParams),pagination.createPagination(queryParams));
    }

    @Override
    public Product create(Product product) {
        return productJpaRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product p = productJpaRepository.getOne(product.getId());
        p.setName(product.getName());
        return productJpaRepository.save(p);
    }

    @Override
    public void delete(Long id) {
        productJpaRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllById(List<Long> ids) {
        if(ids.isEmpty()){
            return new ArrayList<>();
        }
        return productJpaRepository.findAllById(ids);
    }

}
