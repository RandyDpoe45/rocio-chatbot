package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.database.repositories.KnowledgeAreaRepository;
import com.wesdom.rocio.model.KnowledgeArea;
import com.wesdom.rocio.services.KnowledgeAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeAreaServiceImpl implements KnowledgeAreaService {

    @Autowired
    private KnowledgeAreaRepository knowledgeAreaRepository;

    @Override
    public KnowledgeArea create(KnowledgeArea knowledgeArea) {
        return knowledgeAreaRepository.create(knowledgeArea);
    }

    @Override
    public KnowledgeArea update(Long id, KnowledgeArea knowledgeArea) {
        return knowledgeAreaRepository.update(id,knowledgeArea);
    }
}
