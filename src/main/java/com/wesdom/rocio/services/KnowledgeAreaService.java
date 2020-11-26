package com.wesdom.rocio.services;

import com.wesdom.rocio.model.KnowledgeArea;

public interface KnowledgeAreaService {

    KnowledgeArea create(KnowledgeArea knowledgeArea);
    KnowledgeArea update(Long id, KnowledgeArea knowledgeArea);
}
