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
import com.wesdom.rocio.database.repositories.DiseaseRepository;
import com.wesdom.rocio.model.Disease;
import com.wesdom.rocio.model.Expert;
import com.wesdom.rocio.services.DiseaseService;
import com.wesdom.rocio.views.DiseaseViews;
import java.util.Map;

import com.wesdom.rocio.views.ExpertViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author randy
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("rocio/v1/disease")
public class DiseaseRestController {
    
    
    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private DiseaseService diseaseService;
    
    @GetMapping
    @JsonView(DiseaseViews.BasicView.class)
    public Page<Disease> getAll(@RequestParam Map<String, String> queryParams) {
        return diseaseRepository.getAll(queryParams);
    }

    @PostMapping
    @JsonView(DiseaseViews.BasicView.class)
    public Disease create(@RequestBody String data) throws JsonProcessingException {
        Disease disease = decode(data);
        return diseaseService.create(disease);
    }

    @PutMapping("/{id}")
    @JsonView(DiseaseViews.BasicView.class)
    public Disease update(@PathVariable Long id, @RequestBody String data) throws JsonProcessingException {
        Disease disease = decode(data);
        return diseaseService.update(id, disease);
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id){
        diseaseRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Borada con exito");
    }

    private Disease decode(String data) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return o.readerWithView(DiseaseViews.CreateUpdateView.class)
                .forType(Disease.class)
                .readValue(data);
    }
}
