/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.database.repositories.ExpertRepository;
import com.wesdom.rocio.database.repositories.KnowledgeAreaRepository;
import com.wesdom.rocio.model.Expert;
import com.wesdom.rocio.model.KnowledgeArea;
import com.wesdom.rocio.services.ExpertService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author randy
 */
@Service
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    private ExpertRepository expertRepository;
    
    @Autowired
    private KnowledgeAreaRepository knowledgeAreaRepository;
    
    @Override
    public Expert create(Expert expert) {
        List<KnowledgeArea> knowledgeAreas = knowledgeAreaRepository.getAllById(
                expert.getKnowledgeAreas().stream().map(x -> x.getId()).collect(Collectors.toList()));
        expert.setKnowledgeAreas(knowledgeAreas);
        return expertRepository.create(expert);
    }

    @Override
    public Expert update(Long id, Expert expert) {
        List<KnowledgeArea> knowledgeAreas = knowledgeAreaRepository.getAllById(
                expert.getKnowledgeAreas().stream().map(x -> x.getId()).collect(Collectors.toList()));
        expert.setKnowledgeAreas(knowledgeAreas);
        return expertRepository.update(id,expert);
    }
    
    
}
