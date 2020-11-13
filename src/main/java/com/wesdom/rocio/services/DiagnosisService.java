/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.services;

import com.wesdom.rocio.model.Diagnosis;

/**
 *
 * @author randy
 */
public interface DiagnosisService {
    Diagnosis create(Diagnosis diagnosis);
    Diagnosis update(Long id, Diagnosis diagnosis);
}
