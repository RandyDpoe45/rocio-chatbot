/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositories;

import com.wesdom.rocio.model.Diagnosis;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author randy
 */
public interface DiagnosisRepository {
    
    Diagnosis get(Long id);
    Diagnosis create(Diagnosis diagnosis);
    Diagnosis update(Long id, Diagnosis diagnosis);
    void delete(Long id);
    Page<Diagnosis> getAll(Map<String,String> queryParams);
    List<Diagnosis> getAllById(List<Long> ids);
    List<Diagnosis> getByUserIdAndRequestId(List<Long> usersIds, Long requestId);
    
}
