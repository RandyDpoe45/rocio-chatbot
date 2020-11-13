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
import java.util.HashMap;
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
    
}
