/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.database.repositories.GroupRepository;
import com.wesdom.rocio.database.repositories.ManufacturerRepository;
import com.wesdom.rocio.database.repositories.RequestRepository;
import com.wesdom.rocio.model.DiagnosisGroup;
import com.wesdom.rocio.model.Manufacturer;
import com.wesdom.rocio.model.Request;
import com.wesdom.rocio.services.RequestService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author randy
 */
@Service
public class RequestServiceImpl implements RequestService{

    @Autowired
    private RequestRepository requestRepository;
    
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;
    
    @Override
    public Request create(Request request) {
        DiagnosisGroup diagnosisGroup = groupRepository.getLessBusy();
        Manufacturer manufacturer = manufacturerRepository.getByPhone(request.getCelNumber());
        request.setRequestDate(new Date()).setGroup(diagnosisGroup).setManufacturer(manufacturer);
        return requestRepository.create(request);
    }

    @Override
    public Request update(Long id, Request request) {
        DiagnosisGroup g = groupRepository.get(request.getGroup().getId());
        Manufacturer manufacturer = manufacturerRepository.getByPhone(request.getCelNumber());
        request.setGroup(g).setManufacturer(manufacturer);
        return requestRepository.update(id, request);
    }
    
}
