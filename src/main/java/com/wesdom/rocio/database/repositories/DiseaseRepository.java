/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositories;

import com.wesdom.rocio.model.Disease;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author randy
 */
public interface DiseaseRepository {
    Disease get(Long id);
    Disease create(Disease disease);
    Disease update(Long id, Disease disease);
    void delete(Long id);
    Page<Disease> getAll(Map<String,String> queryParams);
    List<Disease> getAllById(List<Long> ids);
}
