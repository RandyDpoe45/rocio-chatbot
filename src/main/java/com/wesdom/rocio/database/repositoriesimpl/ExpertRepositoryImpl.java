/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.ExpertJpaRepository;
import com.wesdom.rocio.database.repositories.ExpertRepository;
import com.wesdom.rocio.model.Expert;
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
public class ExpertRepositoryImpl implements ExpertRepository{
    
    @Autowired
    private ExpertJpaRepository expertJpaRepository;

    @Override
    public Expert get(Long id) {
        return expertJpaRepository.getOne(id);
    }

    @Override
    public Expert create(Expert expert) {
        return expertJpaRepository.save(expert);
    }

    @Override
    public Expert update(Long id, Expert expert) {
        Expert e = expertJpaRepository.getOne(id);
        e.setDiagnosisAsig(expert.getDiagnosisAsig()).setEffectiveness(expert.getEffectiveness()).setUserId(expert.getUserId());
        return expertJpaRepository.save(expert);
    }

    @Override
    public void delete(Long id) {
        expertJpaRepository.deleteById(id);
    }

    @Override
    public Page<Expert> getAll(Map<String, String> queryParams) {
        IPredicateBuilder<Expert> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder pagination = new PaginationBuilderImpl();
        if(queryParams== null){
            queryParams = new HashMap<>();
        }
        return expertJpaRepository.findAll(predicate.createPredicate(queryParams), pagination.createPagination(queryParams));
    }

    @Override
    public List<Expert> getAllById(List<Long> ids) {
        return expertJpaRepository.findAllById(ids);
    }

    @Override
    public Long getAmountOfDiagnosisGroups(Long id) {
        return expertJpaRepository.getAmountOfDiagnosisGroups(id);
    }

}
