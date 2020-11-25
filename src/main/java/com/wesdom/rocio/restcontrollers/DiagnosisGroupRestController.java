/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesdom.rocio.database.repositories.GroupRepository;
import com.wesdom.rocio.model.DiagnosisGroup;
import com.wesdom.rocio.services.DiagnosisGroupService;
import com.wesdom.rocio.views.DiagnosisGroupViews;
import com.wesdom.rocio.views.DiagnosisGroupViews;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author randy
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("rocio/v1/diagnosisGroup")
public class DiagnosisGroupRestController {
    
    @Autowired
    private DiagnosisGroupService diagnosisGroupService;

    @Autowired
    private GroupRepository groupRepository;

    @PostMapping("")
    @JsonView(DiagnosisGroupViews.BasicView.class)
    public DiagnosisGroup create(@RequestBody String data) throws JsonProcessingException {
        DiagnosisGroup diagnosisGroup = decode(data);
        return diagnosisGroupService.create(diagnosisGroup);
    }

    @GetMapping
    @JsonView(DiagnosisGroupViews.BasicView.class)
    public Page<DiagnosisGroup> getAll(@RequestParam Map<String, String> queryParams) {
        return groupRepository.getAll(queryParams);
    }

    @PutMapping("/{id}")
    @JsonView(DiagnosisGroupViews.BasicView.class)
    public DiagnosisGroup update(@PathVariable Long id, @RequestBody String data) {
        DiagnosisGroup r = null;
        try {
            r = decode(data);
            r = diagnosisGroupService.update(id, r);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RequestRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id) {
        groupRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Borado con exito");
    }

    private DiagnosisGroup decode(String data) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return o.readerWithView(DiagnosisGroupViews.CreateUpdateView.class)
                .forType(DiagnosisGroup.class)
                .readValue(data);
    }
    
}
