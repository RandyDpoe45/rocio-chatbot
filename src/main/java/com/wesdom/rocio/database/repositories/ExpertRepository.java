/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositories;

import com.wesdom.rocio.model.Expert;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author randy
 */
public interface ExpertRepository {
    
    Expert get(Long id);
    Expert create(Expert expert);
    Expert update(Long id, Expert expert);
    void delete(Long id);
    Page<Expert> getAll(Map<String,String> queryParams);
    List<Expert> getAllById(List<Long> ids);
}
