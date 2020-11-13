/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.rocio.database.repositories.DiseaseRepository;
import com.wesdom.rocio.model.Disease;
import com.wesdom.rocio.views.DiseaseViews;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping
    @JsonView(DiseaseViews.BasicView.class)
    public Page<Disease> getAll(@RequestParam Map<String, String> queryParams) {
        return diseaseRepository.getAll(queryParams);
    }
}
