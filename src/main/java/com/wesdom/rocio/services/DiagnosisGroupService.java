/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.services;

import com.wesdom.rocio.model.DiagnosisGroup;
import com.wesdom.rocio.model.Request;

/**
 *
 * @author randy
 */
public interface DiagnosisGroupService {
    
    DiagnosisGroup create(DiagnosisGroup group);
    DiagnosisGroup update(Long id, DiagnosisGroup group);
}
