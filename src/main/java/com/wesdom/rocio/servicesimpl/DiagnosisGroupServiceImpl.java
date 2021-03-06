/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.database.repositories.ApprenticeRepository;
import com.wesdom.rocio.database.repositories.GroupRepository;
import com.wesdom.rocio.database.repositories.KnowledgeAreaRepository;
import com.wesdom.rocio.database.repositories.RequestRepository;
import com.wesdom.rocio.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.rocio.exceptionhandling.exceptions.GeneralException;
import com.wesdom.rocio.model.DiagnosisGroup;
import com.wesdom.rocio.model.Request;
import com.wesdom.rocio.services.DiagnosisGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author randy
 */
@Service
public class DiagnosisGroupServiceImpl implements DiagnosisGroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ApprenticeRepository apprenticeRepository;

    @Autowired
    private KnowledgeAreaRepository knowledgeAreaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public DiagnosisGroup create(DiagnosisGroup group) {
        DiagnosisGroup g = groupRepository.create(group);
        return g;
    }

    @Override
    @Transactional
    public DiagnosisGroup update(Long id, DiagnosisGroup group) {
        DiagnosisGroup g = groupRepository.update(id,group);
        return g;
    }

    @Override
    public void delete(Long id) {
        Request request = requestRepository.getTop1ByDiagnosisGroupId(id);
        if(request != null){
            throw new GeneralException(ExceptionCodesEnum.GROUP_WITH_REQUEST,"No se puede eliminar porque tiene solicitudes asociadas");
        }
        groupRepository.delete(id);
    }
}
