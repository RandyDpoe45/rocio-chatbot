/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.GroupJpaRepository;
import com.wesdom.rocio.database.repositories.GroupRepository;
import com.wesdom.rocio.model.DiagnosisGroup;
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
public class GroupRepositoryImpl implements GroupRepository{

    @Autowired
    private GroupJpaRepository groupJpaRepository; 
    
    @Override
    public DiagnosisGroup get(Long id) {
        return groupJpaRepository.getOne(id);
    }

    @Override
    public DiagnosisGroup create(DiagnosisGroup group) {
        return groupJpaRepository.save(group);
    }

    @Override
    public DiagnosisGroup update(Long id, DiagnosisGroup group) {
        DiagnosisGroup g =groupJpaRepository.getOne(id);
        g.setApprentices(group.getApprentices()).setExpert(group.getExpert()).
                setName(group.getName()).setKnowledgeAreas(group.getKnowledgeAreas());
        return  groupJpaRepository.save(g);
    }

    @Override
    public void delete(Long id) {
        groupJpaRepository.deleteById(id);
    }

    @Override
    public Page<DiagnosisGroup> getAll(Map<String, String> queryParams) {
        IPredicateBuilder<DiagnosisGroup> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginationBuilder = new PaginationBuilderImpl();
        if(queryParams == null){
            queryParams = new HashMap<>();
        }
        return groupJpaRepository.findAll(predicate.createPredicate(queryParams), paginationBuilder.createPagination(queryParams));
    }
    
}
