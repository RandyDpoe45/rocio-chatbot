/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositories;

import com.wesdom.rocio.model.Treatment;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author randy
 */
public interface TreatmentRepository {
    Treatment get(Long id);
    Treatment create(Treatment treatment);
    Treatment update(Long id, Treatment treatment);
    void delete(Long id);
    Page<Treatment> getAll(Map<String,String> queryParams);
    List<Treatment> getAllById(List<Long> ids);
    
}
