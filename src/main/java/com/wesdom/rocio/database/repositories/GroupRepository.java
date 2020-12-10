/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositories;

import com.wesdom.rocio.model.DiagnosisGroup;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author randy
 */
public interface GroupRepository {
    DiagnosisGroup get(Long id);
    DiagnosisGroup create(DiagnosisGroup group);
    DiagnosisGroup update(Long id, DiagnosisGroup group);
    void delete(Long id);
    Page<DiagnosisGroup> getAll(Map<String,String> queryParams);
    DiagnosisGroup getLessBusy();
}
