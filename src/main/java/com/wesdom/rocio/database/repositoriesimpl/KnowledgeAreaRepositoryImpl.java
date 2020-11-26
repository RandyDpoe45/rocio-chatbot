/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.KnowledgeAreaJpaRepository;
import com.wesdom.rocio.database.repositories.KnowledgeAreaRepository;
import com.wesdom.rocio.model.DiagnosisGroup;
import com.wesdom.rocio.model.KnowledgeArea;
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
public class KnowledgeAreaRepositoryImpl implements KnowledgeAreaRepository {

    @Autowired
    private KnowledgeAreaJpaRepository knowledgeAreaJpaRepository;

    @Override
    public KnowledgeArea get(Long id) {
        return knowledgeAreaJpaRepository.getOne(id);
    }

    @Override
    public KnowledgeArea create(KnowledgeArea knowledgeArea) {
        return knowledgeAreaJpaRepository.save(knowledgeArea);
    }

    @Override
    public KnowledgeArea update(Long id, KnowledgeArea knowledgeArea) {
        KnowledgeArea ka = knowledgeAreaJpaRepository.getOne(id);
        ka.setDescription(knowledgeArea.getDescription()).setName(knowledgeArea.getName());
        return knowledgeAreaJpaRepository.save(ka);
    }

    @Override
    public void delete(Long id) {
        knowledgeAreaJpaRepository.deleteById(id);
    }

    @Override
    public Page<KnowledgeArea> getAll(Map<String, String> queryParams) {
        IPredicateBuilder<KnowledgeArea> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginationBuilder = new PaginationBuilderImpl();
        if(queryParams == null){
            queryParams = new HashMap<>();
        }
        return knowledgeAreaJpaRepository.findAll(predicate.createPredicate(queryParams), paginationBuilder.createPagination(queryParams));
    }

    @Override
    public List<KnowledgeArea> getAllById(List<Long> ids) {
        return knowledgeAreaJpaRepository.findAllById(ids);
    }
    
    
    
}
