/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.services;

import com.wesdom.rocio.model.Request;

/**
 *
 * @author randy
 */
public interface RequestService {
    
    Request create(Request request);
    Request update(Long id, Request request);
}
