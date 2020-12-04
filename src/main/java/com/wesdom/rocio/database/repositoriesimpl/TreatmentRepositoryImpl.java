/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.TreatmentJpaRepository;
import com.wesdom.rocio.database.repositories.TreatmentRepository;
import com.wesdom.rocio.model.Treatment;
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
public class TreatmentRepositoryImpl implements TreatmentRepository {

    @Autowired
    private TreatmentJpaRepository  treatmentJpaRepository;
    
    @Override
    public Treatment get(Long id) {
        return treatmentJpaRepository.getOne(id);
    }

    @Override
    public Treatment create(Treatment treatment) {
        return treatmentJpaRepository.save(treatment);
    }

    @Override
    public Treatment update(Long id, Treatment treatment) {
        Treatment t = treatmentJpaRepository.getOne(id);
        t.setPreparation(treatment.getPreparation()).setName(treatment.getName()).setBiological
        (treatment.getBiological()).setType(treatment.getType());
        return treatmentJpaRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        treatmentJpaRepository.deleteById(id);
    }

    @Override
    public Page<Treatment> getAll(Map<String, String> queryParams) {
        IPredicateBuilder<Treatment> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder pagination = new PaginationBuilderImpl();
        if(queryParams == null){
            queryParams = new HashMap<>();
        }
        return treatmentJpaRepository.findAll(predicate.createPredicate(queryParams), pagination.createPagination(queryParams));
    }

    @Override
    public List<Treatment> getAllById(List<Long> ids) {
        return treatmentJpaRepository.findAllById(ids);
    }
    
}
