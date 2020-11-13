/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.database.repositories.ApprenticeRepository;
import com.wesdom.rocio.database.repositories.KnowledgeAreaRepository;
import com.wesdom.rocio.model.Apprentice;
import com.wesdom.rocio.model.Apprentice;
import com.wesdom.rocio.model.KnowledgeArea;
import com.wesdom.rocio.services.ApprenticeService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author randy
 */
@Service
public class ApprenticeServiceImpl implements ApprenticeService {

    @Autowired
    private ApprenticeRepository apprenticeRepository;
    
    @Autowired
    private KnowledgeAreaRepository knowledgeAreaRepository;
    
    @Override
    public Apprentice create(Apprentice apprentice) {
        List<KnowledgeArea> knowledgeAreas = knowledgeAreaRepository.getAllById(
                apprentice.getKnowledgeAreas().stream().map(x -> x.getId()).collect(Collectors.toList()));
        apprentice.setKnowledgeAreas(knowledgeAreas);
        return apprenticeRepository.create(apprentice);
    }

    @Override
    public Apprentice update(Long id, Apprentice apprentice) {
        List<KnowledgeArea> knowledgeAreas = knowledgeAreaRepository.getAllById(
                apprentice.getKnowledgeAreas().stream().map(x -> x.getId()).collect(Collectors.toList()));
        apprentice.setKnowledgeAreas(knowledgeAreas);
        return apprenticeRepository.update(id,apprentice);
    }
}
