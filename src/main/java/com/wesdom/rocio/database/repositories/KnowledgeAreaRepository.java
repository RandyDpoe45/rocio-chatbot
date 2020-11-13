/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositories;

import com.wesdom.rocio.model.KnowledgeArea;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author randy
 */
public interface KnowledgeAreaRepository {
    KnowledgeArea get(Long id);
    KnowledgeArea create(KnowledgeArea knowledgeArea);
    KnowledgeArea update(Long id, KnowledgeArea knowledgeArea);
    void delete(Long id);
    Page<KnowledgeArea> getAll(Map<String,String> queryParams);
    List<KnowledgeArea> getAllById(List<Long> ids);
}
