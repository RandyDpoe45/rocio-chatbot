/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositories;

import com.wesdom.rocio.model.Apprentice;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author randy
 */
public interface ApprenticeRepository {
    
    Apprentice get(Long id);
    Apprentice create(Apprentice apprentice);
    Apprentice update(Apprentice apprentice);
    void delete(Long id);
    Page<Apprentice> getAll(Map<String,String> queryParams);
    
}
