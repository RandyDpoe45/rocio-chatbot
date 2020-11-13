/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.GroupJpaRepository;
import com.wesdom.rocio.database.repositories.GroupRepository;
import com.wesdom.rocio.model.DiagnosisGroup;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author randy
 */
@Service
public class GroupRepositoryImpl implements GroupRepository{

    @Autowired
    private GroupJpaRepository groupJpaRepository; 
    
    @Override
    public DiagnosisGroup get(Long id) {
        return groupJpaRepository.getOne(id);
    }

    @Override
    public DiagnosisGroup create(DiagnosisGroup group) {
        return groupJpaRepository.save(group);
    }

    @Override
    public DiagnosisGroup update(Long id, DiagnosisGroup group) {
//        DiagnosisGroup g =groupJpaRepository.getOne(id);
//        g.set
        return  null;
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Page<DiagnosisGroup> getAll(Map<String, String> queryParams) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}