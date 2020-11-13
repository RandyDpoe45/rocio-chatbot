/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.restcontrollers;

import com.wesdom.rocio.database.repositories.ProductRepository;
import com.wesdom.rocio.model.Product;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author randy
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("rocio/v1/product")
public class ProductRestController {
    
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping
    public Page<Product> getAll(@RequestParam Map<String,String> params){
        return productRepository.getAll(params);
    }
}
