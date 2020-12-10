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
import com.wesdom.rocio.database.repositories.ExpertRepository;
import com.wesdom.rocio.model.Expert;
import com.wesdom.rocio.services.ExpertService;
import com.wesdom.rocio.views.ExpertViews;
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
@RequestMapping("rocio/v1/expert")
public class ExpertRestController {
    
    @Autowired
    private ExpertService expertService;
    
    @Autowired
    private ExpertRepository expertRepository;
    
    @PostMapping("")
    @JsonView(ExpertViews.BasicView.class)
    public Expert create(@RequestBody String data) throws JsonProcessingException {
        Expert expert = decode(data);
        return expertService.create(expert);
    }

    @GetMapping
    @JsonView(ExpertViews.BasicView.class)
    public Page<Expert> getAll(@RequestParam Map<String, String> queryParams) {
        return expertRepository.getAll(queryParams);
    }

    @PutMapping("/{id}")
    @JsonView(ExpertViews.BasicView.class)
    public Expert update(@PathVariable Long id, @RequestBody String data) {
        Expert r = null;
        try {
            r = decode(data);
            r = expertService.update(id, r);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RequestRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id) {
        expertService.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Borado con exito");
    }

    private Expert decode(String data) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return o.readerWithView(ExpertViews.CreateUpdateView.class)
                .forType(Expert.class)
                .readValue(data);
    }
}
