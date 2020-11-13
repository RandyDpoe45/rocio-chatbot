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
import com.wesdom.rocio.database.repositories.DiagnosisRepository;
import com.wesdom.rocio.model.Diagnosis;
import com.wesdom.rocio.services.DiagnosisService;
import com.wesdom.rocio.views.DiagnosisViews;
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
@RequestMapping("rocio/v1/diagnosis")
public class DiagnosisRestController {
    
    @Autowired
    private DiagnosisService diagnosisService;
    
    @Autowired
    private DiagnosisRepository diagnosisRepository;
    
    @PostMapping("")
    @JsonView(DiagnosisViews.BasicView.class)
    public Diagnosis create(@RequestBody String data) throws JsonProcessingException {
        Diagnosis diagnosis = decode(data);
        return diagnosisService.create(diagnosis);
    }

    @GetMapping
    @JsonView(DiagnosisViews.BasicView.class)
    public Page<Diagnosis> getAll(@RequestParam Map<String, String> queryParams) {
        return diagnosisRepository.getAll(queryParams);
    }

    @PutMapping("/{id}")
    @JsonView(DiagnosisViews.BasicView.class)
    public Diagnosis update(@PathVariable Long id, @RequestBody String data) {
        Diagnosis r = null;
        try {
            r = decode(data);
            r = diagnosisService.update(id, r);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RequestRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id) {
        diagnosisRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Borado con exito");
    }

    private Diagnosis decode(String data) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return o.readerWithView(DiagnosisViews.CreateUpdateView.class)
                .forType(Diagnosis.class)
                .readValue(data);
    }
}
