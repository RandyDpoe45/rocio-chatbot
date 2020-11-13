/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.services;

import com.wesdom.rocio.model.Apprentice;

/**
 *
 * @author randy
 */
public interface ApprenticeService {
    Apprentice create(Apprentice apprentice);
    Apprentice update(Long id, Apprentice apprentice);
}
