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
import com.wesdom.rocio.database.repositories.RequestRepository;
import com.wesdom.rocio.database.repositories.TreatmentRepository;
import com.wesdom.rocio.model.*;
import com.wesdom.rocio.model.enums.RequestStatus;
import com.wesdom.rocio.services.DiagnosisService;

import java.util.Arrays;
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
    private RequestRepository requestRepository;
    
    @Autowired
    private DiagnosisRepository diagnosisRepository; 
    
    @Override
    public Diagnosis create(Diagnosis diagnosis) {
        List<Treatment> treatments = treatmentRepository.getAllById(
                diagnosis.getTreatments().stream().map(x -> x.getId()).collect(Collectors.toList()));
        List<Disease> diseases = diseaseRepository.getAllById(diagnosis.getDiseases().
                stream().map(x -> x.getId()).collect( Collectors.toList()));
        AppUser user = userJpaRepository.getOne(diagnosis.getUser().getId());
        Request request = requestRepository.get(diagnosis.getRequest().getId());
        diagnosis.setDiseases(diseases).setTreatments(treatments).setUser(user).setRequest(request);
        Diagnosis d  = diagnosisRepository.create(diagnosis);
        if(!Arrays.asList(RequestStatus.PD.name(),RequestStatus.AM.name(),RequestStatus.AA.name()).contains(request.getStatus())){
            String requestStatus = getRequestState(user,request);
            request.setStatus(requestStatus);
            requestRepository.create(request);
        }
        return d;
    }

    @Override
    public Diagnosis update(Long id, Diagnosis diagnosis) {
        List<Treatment> treatments = treatmentRepository.getAllById(
                diagnosis.getTreatments().stream().map(x -> x.getId()).collect(Collectors.toList()));
        List<Disease> diseases = diseaseRepository.getAllById(diagnosis.getDiseases().
                stream().map(x -> x.getId()).collect( Collectors.toList()));
        AppUser user = userJpaRepository.getOne(diagnosis.getUser().getId());
        Request request = requestRepository.get(diagnosis.getId());
        diagnosis.setDiseases(diseases).setTreatments(treatments).setUser(user).setRequest(request);
        Diagnosis d  = diagnosisRepository.update(id,diagnosis);
        if(!Arrays.asList(RequestStatus.PD.name(),RequestStatus.AM.name(),RequestStatus.AA.name()).contains(request.getStatus())){
            String requestStatus = getRequestState(user,request);
            request.setStatus(requestStatus);
            requestRepository.create(request);
        }
        return d;
    }

    private String getRequestState(AppUser user, Request request){
        List<Long> userIds = request.getGroup().getApprentices().stream().map(x -> x.getId()).collect(Collectors.toList());
        if(user instanceof  Expert){
            System.out.println("!!!!!!!---------------"+user.getUserId());
        }
        List<Diagnosis> diagnosis = diagnosisRepository.getByUserIdAndRequestId(userIds,request.getId());
        if (diagnosis.size() > 0 && diagnosis.size() <userIds.size()){
            return RequestStatus.EP.name();
        }else if(diagnosis.size() == userIds.size()){
            Diagnosis diag = diagnosis.get(0);
            for(Diagnosis d : diagnosis){
                List<Treatment> treatments = d.getTreatments();
                List<Disease> diseases = d.getDiseases();
                if(treatments.size() != diag.getTreatments().size() || diseases.size() != diag.getDiseases().size()){
                    return RequestStatus.PD.name();
                }
                for(Treatment t : diag.getTreatments()){
                    if(!treatments.contains(t)){
                        return RequestStatus.PD.name();
                    }
                }
                for(Disease di : diag.getDiseases()){
                    if(!diseases.contains(di)){
                        return RequestStatus.PD.name();
                    }
                }
            }
            return RequestStatus.AA.name();
        }
        return RequestStatus.EE.name();
    }
    
}
