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
import com.wesdom.rocio.database.repositories.RequestRepository;
import com.wesdom.rocio.dtos.WebhookDto;
import com.wesdom.rocio.model.*;
import com.wesdom.rocio.model.enums.RequestStatus;
import com.wesdom.rocio.services.DiagnosisService;
import com.wesdom.rocio.views.DiagnosisViews;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.json.JSONException;
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
@RequestMapping("rocio/v1/diagnosis")
public class DiagnosisRestController {
    
    @Autowired
    private DiagnosisService diagnosisService;
    
    @Autowired
    private DiagnosisRepository diagnosisRepository;
    
    @Autowired
    private RequestRepository requestRepository;
    
    @PostMapping("")
    @JsonView(DiagnosisViews.BasicView.class)
    public Diagnosis create(@RequestBody String data) throws JsonProcessingException {
        Diagnosis diagnosis = decode(data);
        return diagnosisService.create(diagnosis);
    }

    @PostMapping("/bot")
    public WebhookDto getTreatmentBot(@RequestParam Map<String,String> requestBody ) {
        try {
            JSONObject request = new JSONObject(requestBody);
            String reqData = request.getString("reqSol");
            System.out.println("!!!!----"+reqData);
            Pattern p = Pattern.compile("\\#([0-9]+)\\#");
            Matcher m = p.matcher(reqData);
            if(m.find()){
                Long reqId = Long.parseLong(m.group().replace("#","").trim());
                Request req = requestRepository.get(reqId);
                List<Diagnosis> diagnoses = null;
                if (req.getStatus().equals(RequestStatus.AA.name())) {
                    List<Long> ids = req.getGroup().getApprentices().stream().map(x -> x.getId()).collect(Collectors.toList());
                    diagnoses = diagnosisRepository.getByUserIdAndRequestId(ids, req.getId());
                } else {
                    diagnoses = diagnosisRepository.getByUserIdAndRequestId(Arrays.asList(req.getGroup().getExpert().getId()), req.getId());
                }
                String response = "Estas fueron las enfermedades diagnosticadas para el request #"+reqId+"# : \n";
                for (Disease d : diagnoses.get(0).getDiseases()) {
                    response += d.getName() + " \n";
                }
                response += "Estos son los tratamientos sugeridos:\n";
                for (Treatment t : diagnoses.get(0).getTreatments()) {
                    response += t.getPreparation() + " \n";
                }
                return new WebhookDto().setUser_id(request.getString("user_id")).setBot_id(request.getString("bot_id")).
                        setBlocked_input(Boolean.TRUE).setChannel(request.getString("channel")).setModule_id(request.getString("module_id")).
                        setMessage(response);
            }else{
                return new WebhookDto().setUser_id(request.getString("user_id")).setBot_id(request.getString("bot_id")).
                        setBlocked_input(Boolean.TRUE).setChannel(request.getString("channel")).setModule_id(request.getString("module_id")).
                        setMessage("No se encuentra diagnostico para esta peticion");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new WebhookDto();
        }
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
