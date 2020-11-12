/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.RequestJpaRepository;
import com.wesdom.rocio.database.repositories.RequestRepository;
import com.wesdom.rocio.model.Request;
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
public class RequestRepositoryImpl implements RequestRepository{
    
    @Autowired
    private RequestJpaRepository requestJpaRepository;

    @Override
    public Request get(Long id) {
        return requestJpaRepository.getOne(id);
    }

    @Override
    public Request create(Request request) {
        return requestJpaRepository.save(request);
    }

    @Override
    public Request update(Long id, Request request) {
        Request req = requestJpaRepository.getOne(id);
        req.setGroup(request.getGroup()).setEmail(request.getEmail()).setCelNumber(request.getCelNumber())
                .setDescription(request.getDescription()).setStatus(request.getStatus()).setProduct(request.getProduct()).
                setVariety(request.getVariety()).setDiseaseTime(request.getDiseaseTime()).setAmountOfPlants(request.getAmountOfPlants());
        return requestJpaRepository.save(req);
    }

    @Override
    public void delete(Long id) {
        requestJpaRepository.deleteById(id);
    }

    @Override
    public Page<Request> getAll(Map<String, String> queryParams) {
        IPredicateBuilder<Request> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder pagination = new PaginationBuilderImpl();
        if(queryParams == null){
            queryParams = new HashMap<>();
        }
        return requestJpaRepository.findAll(predicate.createPredicate(queryParams), pagination.createPagination(queryParams));
    }
    
    
}
