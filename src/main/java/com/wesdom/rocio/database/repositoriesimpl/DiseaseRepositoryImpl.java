/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.DiseaseJpaRepository;
import com.wesdom.rocio.database.repositories.DiseaseRepository;
import com.wesdom.rocio.model.Disease;
import com.wesdom.rocio.services.IPaginationBuilder;
import com.wesdom.rocio.services.IPredicateBuilder;
import com.wesdom.rocio.servicesimpl.PaginationBuilderImpl;
import com.wesdom.rocio.servicesimpl.PredicateBuilderServiceImpl;
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
public class DiseaseRepositoryImpl implements DiseaseRepository{

    @Autowired
    private DiseaseJpaRepository diseaseJpaRepository; 
    
    @Override
    public Disease get(Long id) {
        return diseaseJpaRepository.getOne(id);
    }

    @Override
    public Disease create(Disease disease) {
        return diseaseJpaRepository.save(disease);
    }

    @Override
    public Disease update(Long id, Disease disease) {
        Disease d = diseaseJpaRepository.getOne(id);
        d.setName(disease.getName()).setProducts(disease.getProducts()).setScientificName(disease.getScientificName())
                .setTreatments(disease.getTreatments());
        return diseaseJpaRepository.save(d);
    }

    @Override
    public void delete(Long id) {
        diseaseJpaRepository.deleteById(id);
    }

    @Override
    public Page<Disease> getAll(Map<String, String> queryParams) {
        IPredicateBuilder<Disease> predicateBuilder = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginationBuilder = new PaginationBuilderImpl();
        if(queryParams == null){
            queryParams = new HashMap<>();
        }
        return diseaseJpaRepository.findAll(predicateBuilder.createPredicate(queryParams), 
                paginationBuilder.createPagination(queryParams));
    }

    @Override
    public List<Disease> getAllById(List<Long> ids) {
        return diseaseJpaRepository.findAllById(ids);
    }
    
}
