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
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author randy
 */
@Service
public class GroupRepositoryImpl implements GroupRepository{

    @Autowired
    private GroupJpaRepository groupJpaRepository;

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public DiagnosisGroup get(Long id) {
        return groupJpaRepository.getOne(id);
    }

    @Override
    @Transactional
    public DiagnosisGroup create(DiagnosisGroup group) {
        DiagnosisGroup g = groupJpaRepository.saveAndFlush(group);
        entityManager.refresh(g);
        return g;
    }

    @Override
    @Transactional
    public DiagnosisGroup update(Long id, DiagnosisGroup group) {
        DiagnosisGroup g =groupJpaRepository.getOne(id);
        g.setApprentices(group.getApprentices()).setExpert(group.getExpert()).
                setName(group.getName()).setKnowledgeAreas(group.getKnowledgeAreas());

        g = groupJpaRepository.saveAndFlush(g);
        entityManager.refresh(g);
        return  g;
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

    @Override
    public DiagnosisGroup getLessBusy() {
        List<Object[]> res =  groupJpaRepository.findTop1OrderByNumberOfRequestDesc(PageRequest.of(0,1));
        return (DiagnosisGroup) res.get(0)[0];
    }

}
