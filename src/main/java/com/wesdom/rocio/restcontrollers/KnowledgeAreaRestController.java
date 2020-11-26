package com.wesdom.rocio.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesdom.rocio.database.repositories.KnowledgeAreaRepository;
import com.wesdom.rocio.model.KnowledgeArea;
import com.wesdom.rocio.services.KnowledgeAreaService;
import com.wesdom.rocio.views.KnowledgeAreaViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("rocio/v1/knowledgeArea")
public class KnowledgeAreaRestController {
    
    @Autowired
    private KnowledgeAreaRepository knowledgeAreaRepository;
    
    @Autowired
    private KnowledgeAreaService knowledgeAreaService;

    @PostMapping("")
    @JsonView(KnowledgeAreaViews.BasicView.class)
    public KnowledgeArea create(@RequestBody String data) throws JsonProcessingException {
        KnowledgeArea expert = decode(data);
        return knowledgeAreaService.create(expert);
    }

    @GetMapping
    @JsonView(KnowledgeAreaViews.BasicView.class)
    public Page<KnowledgeArea> getAll(@RequestParam Map<String, String> queryParams) {
        return knowledgeAreaRepository.getAll(queryParams);
    }

    @PutMapping("/{id}")
    @JsonView(KnowledgeAreaViews.BasicView.class)
    public KnowledgeArea update(@PathVariable Long id, @RequestBody String data) {
        KnowledgeArea r = null;
        try {
            r = decode(data);
            r = knowledgeAreaService.update(id, r);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RequestRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id) {
        knowledgeAreaRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Borado con exito");
    }

    private KnowledgeArea decode(String data) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return o.readerWithView(KnowledgeAreaViews.CreateUpdateView.class)
                .forType(KnowledgeArea.class)
                .readValue(data);
    }
}
