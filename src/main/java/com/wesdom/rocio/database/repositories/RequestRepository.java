/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositories;

import com.wesdom.rocio.model.DiagnosisGroup;
import com.wesdom.rocio.model.Request;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author randy
 */
public interface RequestRepository {
    
    Request get(Long id);
    Request create(Request request);
    Request update(Long id, Request request);
    void delete(Long id);
    Page<Request> getAll(Map<String,String> queryParams);
    Request getTop1ByDiagnosisGroupId(Long diagnosisGroupId);
    
}
