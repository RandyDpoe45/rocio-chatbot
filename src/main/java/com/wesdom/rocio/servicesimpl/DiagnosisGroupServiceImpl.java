/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.database.repositories.GroupRepository;
import com.wesdom.rocio.model.DiagnosisGroup;
import com.wesdom.rocio.services.DiagnosisGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author randy
 */
@Service
public class DiagnosisGroupServiceImpl implements DiagnosisGroupService {

//    @Autowired
//    private Expert
    @Autowired
    private GroupRepository groupRepository;
            
    @Override
    public DiagnosisGroup create(DiagnosisGroup group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DiagnosisGroup update(Long id, DiagnosisGroup group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
