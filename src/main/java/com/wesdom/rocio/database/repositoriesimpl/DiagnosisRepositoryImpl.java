/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.DiagnosisJpaRepository;
import com.wesdom.rocio.database.jparepositories.DiseaseJpaRepository;
import com.wesdom.rocio.database.jparepositories.TreatmentJpaRepository;
import com.wesdom.rocio.database.repositories.DiagnosisRepository;
import com.wesdom.rocio.model.AppUser;
import com.wesdom.rocio.model.Diagnosis;
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
public class DiagnosisRepositoryImpl implements DiagnosisRepository {

    @Autowired
    private DiagnosisJpaRepository  diagnosisJpaRepository;
    
    @Autowired
    private DiseaseJpaRepository diseaseJpaRepository;
    
    @Autowired
    private TreatmentJpaRepository treatmentJpaRepository; 
    
    @Override
    public Diagnosis get(Long id) {
        return diagnosisJpaRepository.getOne(id);
    }

    @Override
    public Diagnosis create(Diagnosis diagnosis) {
        return diagnosisJpaRepository.save(diagnosis);
    }

    @Override
    public Diagnosis update(Long id, Diagnosis diagnosis) {
        Diagnosis diag = diagnosisJpaRepository.getOne(id);
        diag.setDiseases(diagnosis.getDiseases()).setTreatments(diagnosis.getTreatments()).setUser(diagnosis.getUser()).
                setRequest(diagnosis.getRequest());
        return diagnosisJpaRepository.save(diag);
    }

    @Override
    public void delete(Long id) {
        diagnosisJpaRepository.deleteById(id);
    }

    @Override
    public Page<Diagnosis> getAll(Map<String, String> queryParams) {
        IPredicateBuilder<Diagnosis> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginationBuilder = new PaginationBuilderImpl();
        if(queryParams == null){
            queryParams = new HashMap<>();
        }
        return diagnosisJpaRepository.findAll(predicate.createPredicate(queryParams), paginationBuilder.createPagination(queryParams));
    }

    @Override
    public List<Diagnosis> getAllById(List<Long> ids) {
        return diagnosisJpaRepository.findAllById(ids);
    }

    @Override
    public List<Diagnosis> getByUserIdAndRequestId(List<Long> usersIds, Long requestId){
        return diagnosisJpaRepository.getByUserIdAndRequestId(usersIds,requestId);
    }
    
}
