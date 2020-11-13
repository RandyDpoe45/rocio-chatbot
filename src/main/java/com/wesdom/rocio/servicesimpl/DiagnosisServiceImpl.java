/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.database.jparepositories.DiagnosisJpaRepository;
import com.wesdom.rocio.database.jparepositories.UserJpaRepository;
import com.wesdom.rocio.database.repositories.DiagnosisRepository;
import com.wesdom.rocio.database.repositories.DiseaseRepository;
import com.wesdom.rocio.database.repositories.TreatmentRepository;
import com.wesdom.rocio.model.Diagnosis;
import com.wesdom.rocio.model.Disease;
import com.wesdom.rocio.model.Treatment;
import com.wesdom.rocio.model.AppUser;
import com.wesdom.rocio.services.DiagnosisService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author randy
 */
@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private UserJpaRepository userJpaRepository; 
    
    @Autowired
    private DiseaseRepository diseaseRepository; 
    
    @Autowired
    private TreatmentRepository treatmentRepository;
    
    @Autowired
    private DiagnosisRepository diagnosisRepository; 
    
    @Override
    public Diagnosis create(Diagnosis diagnosis) {
        List<Treatment> treatments = treatmentRepository.getAllById(
                diagnosis.getTreatments().stream().map(x -> x.getId()).collect(Collectors.toList()));
        List<Disease> diseases = diseaseRepository.getAllById(diagnosis.getDiseases().
                stream().map(x -> x.getId()).collect( Collectors.toList()));
        AppUser user = userJpaRepository.getOne(diagnosis.getUser().getId());
        diagnosis.setDiseases(diseases).setTreatments(treatments).setUser(user);
        return diagnosisRepository.create(diagnosis);
    }

    @Override
    public Diagnosis update(Long id, Diagnosis diagnosis) {
        List<Treatment> treatments = treatmentRepository.getAllById(
                diagnosis.getTreatments().stream().map(x -> x.getId()).collect(Collectors.toList()));
        List<Disease> diseases = diseaseRepository.getAllById(diagnosis.getDiseases().
                stream().map(x -> x.getId()).collect( Collectors.toList()));
        AppUser user = userJpaRepository.getOne(diagnosis.getUser().getId());
        diagnosis.setDiseases(diseases).setTreatments(treatments).setUser(user);
        return diagnosisRepository.update(id, diagnosis);
    }
    
}
