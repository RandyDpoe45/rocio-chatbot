package com.wesdom.rocio.services;

import com.wesdom.rocio.model.Expert;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author randy
 */
public interface ExpertService {
    
    Expert create(Expert expert);
    Expert update(Long id, Expert expert);
    
}
