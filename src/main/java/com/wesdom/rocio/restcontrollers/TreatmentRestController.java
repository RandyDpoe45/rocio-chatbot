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
import com.wesdom.rocio.database.repositories.TreatmentRepository;
import com.wesdom.rocio.model.*;
import com.wesdom.rocio.model.Treatment;
import com.wesdom.rocio.views.*;
import com.wesdom.rocio.views.TreatmentViews;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author randy
 */

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("rocio/v1/treatment")
public class TreatmentRestController {
    
    
    @Autowired
    private TreatmentRepository treatmentRepository;
    
    @GetMapping
    @JsonView(TreatmentViews.BasicView.class)
    public Page<Treatment> getAll(@RequestParam Map<String, String> queryParams) {
        return treatmentRepository.getAll(queryParams);
    }

    @PostMapping("")
    @JsonView(TreatmentViews.BasicView.class)
    public Treatment create(@RequestBody String data) throws JsonProcessingException {
        Treatment diagnosisGroup = decode(data);
        return treatmentRepository.create(diagnosisGroup);
    }

    @PutMapping("/{id}")
    @JsonView(TreatmentViews.BasicView.class)
    public Treatment update(@PathVariable Long id, @RequestBody String data) throws JsonProcessingException {
        Treatment
                r = decode(data);
        r = treatmentRepository.update(id, r);
        return r;
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id) {
        treatmentRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Borado con exito");
    }

    private Treatment decode(String data) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return o.readerWithView(TreatmentViews.CreateUpdateView.class)
                .forType(Treatment.class)
                .readValue(data);
    }

}
