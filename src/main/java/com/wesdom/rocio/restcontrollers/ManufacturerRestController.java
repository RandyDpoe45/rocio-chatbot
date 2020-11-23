package com.wesdom.rocio.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesdom.rocio.database.repositories.ManufacturerRepository;
import com.wesdom.rocio.model.Manufacturer;
import com.wesdom.rocio.services.ManufacturerService;
import com.wesdom.rocio.views.ManufacturerViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("rocio/v1/manufacturer")
public class ManufacturerRestController {

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @PostMapping("")
    @JsonView(ManufacturerViews.BasicView.class)
    public Manufacturer create(@RequestBody String data) throws JsonProcessingException {
        Manufacturer manufacturer = decode(data);
        return manufacturerService.create(manufacturer);
    }

    @GetMapping
    @JsonView(ManufacturerViews.BasicView.class)
    public Page<Manufacturer> getAll(@RequestParam Map<String, String> queryParams) {
        return manufacturerRepository.getAll(queryParams);
    }

    @PutMapping("/{id}")
    @JsonView(ManufacturerViews.BasicView.class)
    public Manufacturer update(@PathVariable Long id, @RequestBody String data) {
        Manufacturer r = null;
        try {
            r = decode(data);
            r = manufacturerService.update(id, r);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RequestRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id) {
        manufacturerRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Borado con exito");
    }

    private Manufacturer decode(String data) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return o.readerWithView(ManufacturerViews.CreateUpdateView.class)
                .forType(Manufacturer.class)
                .readValue(data);
    }
}