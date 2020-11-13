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
import com.wesdom.rocio.database.repositories.RequestRepository;
import com.wesdom.rocio.model.Request;
import com.wesdom.rocio.services.RequestService;
import com.wesdom.rocio.views.RequestViews;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
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
@RequestMapping("rocio/v1/request")
public class RequestRestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestRepository requestRepository;

    @PostMapping("")
    @JsonView(RequestViews.BasicView.class)
    public Request create(@RequestBody String chatBotData) {
        try {
            JSONObject data = new JSONObject(chatBotData).getJSONObject("variables");
            Request r = new Request().setAmountOfPlants(data.getString("amountOfPlants")).setCelNumber(data.getString("celNumber")).
                    setDescription(data.getString("description")).setDiseaseTime(data.getString("diseaseTime")).setEmail(data.getString("email")).
                    setProduct(data.getString("product")).setStatus("AA").setVariety(data.getString("variety"));
            return requestService.create(r);
        } catch (Exception e) {
            e.printStackTrace();
            return new Request();
        }
    }

    @GetMapping
    @JsonView(RequestViews.BasicView.class)
    public Page<Request> getAll(@RequestParam Map<String, String> queryParams) {
        return requestRepository.getAll(queryParams);
    }

    @PutMapping("/{id}")
    @JsonView(RequestViews.BasicView.class)
    public Request update(@PathVariable Long id, @RequestBody String data) {
        Request r = null;
        try {
            r = decode(data);
            r = requestRepository.update(id, r);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RequestRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id) {
        requestRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Borada con exito");
    }

    private Request decode(String data) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return o.readerWithView(RequestViews.CreateUpdateView.class)
                .forType(Request.class)
                .readValue(data);
    }
}
