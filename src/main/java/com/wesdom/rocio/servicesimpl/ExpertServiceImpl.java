/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.database.repositories.ExpertRepository;
import com.wesdom.rocio.database.repositories.KnowledgeAreaRepository;
import com.wesdom.rocio.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.rocio.exceptionhandling.exceptions.GeneralException;
import com.wesdom.rocio.model.Expert;
import com.wesdom.rocio.model.KnowledgeArea;
import com.wesdom.rocio.services.ExpertService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author randy
 */
@Service
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    private ExpertRepository expertRepository;
    
    @Autowired
    private KnowledgeAreaRepository knowledgeAreaRepository;
    
    @Override
    public Expert create(Expert expert) {
        return expertRepository.create(expert);
    }

    @Override
    public Expert update(Long id, Expert expert) {
        return expertRepository.update(id,expert);
    }

    @Override
    public void delete(Long id) {
        Expert ex = expertRepository.get(id);
        if(ex.getAmountOfGroups() > 0){
            throw new GeneralException(ExceptionCodesEnum.EXPERT_WITH_GROUPS,"El experto tiene grupos asociados");
        }
        expertRepository.delete(id);
    }


}
