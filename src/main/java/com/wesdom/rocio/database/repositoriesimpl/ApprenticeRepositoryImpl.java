/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.ApprenticeJpaRepository;
import com.wesdom.rocio.database.repositories.ApprenticeRepository;
import com.wesdom.rocio.model.Apprentice;
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
public class ApprenticeRepositoryImpl implements ApprenticeRepository{
    
    @Autowired
    private ApprenticeJpaRepository apprenticeJpaRepository;

    @Override
    public Apprentice get(Long id) {
        return apprenticeJpaRepository.getOne(id);
    }

    @Override
    public Apprentice create(Apprentice apprentice) {
        return apprenticeJpaRepository.save(apprentice);
    }

    @Override
    public Apprentice update(Long id, Apprentice apprentice) {
        Apprentice app = apprenticeJpaRepository.getOne(id);
        app.setDiagnosisAsig(apprentice.getDiagnosisAsig()).setEffectiveness(apprentice.getEffectiveness()).
                setKnowledgeAreas(apprentice.getKnowledgeAreas()).setUserId(apprentice.getUserId());
        return apprenticeJpaRepository.save(app);
    }

    @Override
    public void delete(Long id) {
        apprenticeJpaRepository.deleteById(id);
    }

    @Override
    public Page<Apprentice> getAll(Map<String, String> queryParams) {
        IPredicateBuilder<Apprentice> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginationBuilder = new PaginationBuilderImpl();
        if(queryParams == null){
            queryParams = new HashMap<>();
        }
        return apprenticeJpaRepository.findAll(predicate.createPredicate(queryParams), 
                paginationBuilder.createPagination(queryParams));
    }

    @Override
    public List<Apprentice> getAllById(List<Long> ids) {
        return apprenticeJpaRepository.findAllById(ids);
    }
    
    
}
