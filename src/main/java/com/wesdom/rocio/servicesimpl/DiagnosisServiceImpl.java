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
import com.wesdom.rocio.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.rocio.exceptionhandling.exceptions.GeneralException;
import com.wesdom.rocio.model.*;
import com.wesdom.rocio.model.enums.RequestStatus;
import com.wesdom.rocio.services.DiagnosisService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        AppUser user = diagnosis.getUser();
        Request request = requestRepository.get(diagnosis.getRequest().getId());
        if(alreadyAnswered(user,request)){
            throw new GeneralException(ExceptionCodesEnum.ALREADY_DIAGNOSED,"Ya realizo un diagnostico de esta peticion");
        }
        try {
            diagnosis.setCreationDate(sdf.parse(sdf.format(date)));
            diagnosis.setCreationDateHour(sdf1.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Diagnosis d  = diagnosisRepository.create(diagnosis);
        if(!Arrays.asList(RequestStatus.AM.name(),RequestStatus.AA.name()).contains(request.getStatus())){
            String requestStatus = getRequestState(user,request);
            request.setStatus(requestStatus);
            requestRepository.create(request);
        }
        return d;
    }

    @Override
    public Diagnosis update(Long id, Diagnosis diagnosis) {
        AppUser user = userJpaRepository.getOne(diagnosis.getUser().getId());
        Request request = requestRepository.get(diagnosis.getId());
        Diagnosis d  = diagnosisRepository.update(id,diagnosis);
        if(!Arrays.asList(RequestStatus.AM.name(),RequestStatus.AA.name()).contains(request.getStatus())){
            String requestStatus = getRequestState(user,request);
            request.setStatus(requestStatus);
            requestRepository.create(request);
        }
        return d;
    }

    private boolean alreadyAnswered(AppUser user,Request request){
        List<Diagnosis> diagnosis = diagnosisRepository.getByUserIdAndRequestId(Arrays.asList(user.getId()),request.getId());
        if(!diagnosis.isEmpty()){
            return true;
        }
        return false;
    }

    private String getRequestState(AppUser user, Request request){
        if(request.getGroup().getExpert().getId().equals(user.getId())){
            return RequestStatus.AM.name();
        }
        List<Long> userIds = request.getGroup().getApprentices().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Diagnosis> diagnosis = diagnosisRepository.getByUserIdAndRequestId(userIds,request.getId());
        if (diagnosis.size() > 0 && diagnosis.size() < request.getGroup().getMinimumResponses()){
            return RequestStatus.EP.name();
        }else if(diagnosis.size() >= request.getGroup().getMinimumResponses()){
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
