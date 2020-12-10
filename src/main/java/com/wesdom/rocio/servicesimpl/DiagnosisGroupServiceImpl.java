/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.database.repositories.ApprenticeRepository;
import com.wesdom.rocio.database.repositories.GroupRepository;
import com.wesdom.rocio.database.repositories.KnowledgeAreaRepository;
import com.wesdom.rocio.model.Apprentice;
import com.wesdom.rocio.model.DiagnosisGroup;
import com.wesdom.rocio.model.KnowledgeArea;
import com.wesdom.rocio.services.DiagnosisGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author randy
 */
@Service
public class DiagnosisGroupServiceImpl implements DiagnosisGroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ApprenticeRepository apprenticeRepository;

    @Autowired
    private KnowledgeAreaRepository knowledgeAreaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public DiagnosisGroup create(DiagnosisGroup group) {
        List<Long> knowledgeAreaIds = group.getKnowledgeAreas().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Long> apprendiceIds = group.getApprentices().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Apprentice> apprentices = apprenticeRepository.getAllById(apprendiceIds);
        List<KnowledgeArea> knowledgeAreas = knowledgeAreaRepository.getAllById(knowledgeAreaIds);
        group.setApprentices(apprentices).setKnowledgeAreas(knowledgeAreas);
        DiagnosisGroup g = groupRepository.create(group);
        em.refresh(g);
        return g;
    }

    @Override
    @Transactional
    public DiagnosisGroup update(Long id, DiagnosisGroup group) {
        List<Long> knowledgeAreaIds = group.getKnowledgeAreas().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Long> apprendiceIds = group.getApprentices().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Apprentice> apprentices = apprenticeRepository.getAllById(apprendiceIds);
        List<KnowledgeArea> knowledgeAreas = knowledgeAreaRepository.getAllById(knowledgeAreaIds);
        group.setApprentices(apprentices).setKnowledgeAreas(knowledgeAreas);
        DiagnosisGroup g = groupRepository.update(id,group);
        em.refresh(g);
        return g;
    }
    
}
