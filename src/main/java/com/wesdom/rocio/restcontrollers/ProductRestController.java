/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesdom.rocio.database.repositories.ProductRepository;
import com.wesdom.rocio.model.Product;
import com.wesdom.rocio.model.Product;
import java.util.Map;

import com.wesdom.rocio.services.ProductService;
import com.wesdom.rocio.views.ProductViews;
import com.wesdom.rocio.views.ProductViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    
    @Autowired
    private ProductService productService;
    
    @GetMapping
    public Page<Product> getAll(@RequestParam Map<String,String> params){
        return productRepository.getAll(params);
    }

    @PostMapping
    @JsonView(ProductViews.BasicView.class)
    public Product create(@RequestBody String data) throws JsonProcessingException {
        Product product = decode(data);
        return productService.create(product);
    }

    @PutMapping("/{id}")
    @JsonView(ProductViews.BasicView.class)
    public Product update(@PathVariable Long id, @RequestBody String data) throws JsonProcessingException {
        Product product = decode(data);
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id){
        productRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Borada con exito");
    }

    private Product decode(String data) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return o.readerWithView(ProductViews.CreateUpdateView.class)
                .forType(Product.class)
                .readValue(data);
    }
}
