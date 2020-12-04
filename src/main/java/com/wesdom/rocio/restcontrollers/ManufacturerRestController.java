package com.wesdom.rocio.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesdom.rocio.database.repositories.ManufacturerRepository;
import com.wesdom.rocio.dtos.WebhookDto;
import com.wesdom.rocio.model.Manufacturer;
import com.wesdom.rocio.services.ManufacturerService;
import com.wesdom.rocio.views.ManufacturerViews;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
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

    @PostMapping("/bot")
    @JsonView(ManufacturerViews.BasicView.class)
    public Manufacturer createFromBot(@RequestBody String chatBotData) throws JsonProcessingException {
        try {
            JSONObject data = new JSONObject(chatBotData).getJSONObject("variables");
            System.out.println("!!!data: " + data.toString());
            Manufacturer manufacturer = new Manufacturer().setNames(data.getString("names")).setLastNames(data.getString("lastNames")).
            setPhone(data.getString("phone")).setRole(data.getString("role")).setAge(data.getInt("age")).setIdType(data.getString("idType"))
            .setIdNumber(data.getString("idNumber")).setProdType(data.getString("prod_type")).setPlantationName(data.getString("plantation_field"))
            .setGender(data.getString("gender")).setDepartmentName(data.getString("department")).setMunicipalityName(data.getString("municipality"))
            ;
            return manufacturerService.create(manufacturer);
        }catch (Exception e){
            e.printStackTrace();
            return new Manufacturer();
        }
    }

    @PostMapping("/bot/get")
    public WebhookDto getRegisteredManufacturer(@RequestParam Map<String,String> requestBody ){
        try{
            JSONObject request = new JSONObject(requestBody);
            String phone = request.getString("phone");
            Manufacturer m = manufacturerRepository.getByPhone(phone);
            String response = m!= null ? "El Usuario posee la siguiente informacion\n" : "El usuario no pudo ser creado";
            if(m != null) {
                response += "Nombres: " + (m.getNames() != null ? m.getNames() : "") + "\n";
                response += "Apellidos: " + (m.getLastNames() != null ? m.getLastNames() : "") + "\n";
                response += "Celular: " + (m.getPhone() != null ? m.getPhone() : "") + "\n";
                response += "Edad: " + (m.getAge() != null ? m.getAge() : "") + "\n";
                response += "Genero: " + (m.getGender() != null ? m.getGender() : "") + "\n";
                response += "Tipo de documento: " + (m.getIdType() != null ? m.getIdType() : "") + "\n";
                response += "Numero documento: " + (m.getIdNumber() != null ? m.getIdNumber() : "") + "\n";
                response += "Rol: " + (m.getRole() != null ? m.getRole() : "") + "\n";
                response += "Tipo produccion: " + (m.getProdType() != null ? m.getProdType() : "") + "\n";
                response += "Departamento: " + (m.getDepartmentName() != null ? m.getDepartmentName() : "") + "\n";
                response += "Municipio: " + (m.getMunicipalityName() != null ? m.getMunicipalityName() : "") + "\n";
                response += "Vereda: " + (m.getPlantationName() != null ? m.getPlantationName() : "");
            }
            response+= "\n\nContinuar?";
            List<String> suggestedRep = Arrays.asList("si");
            return new WebhookDto().setUser_id(request.getString("user_id")).setBot_id(request.getString("bot_id")).
                    setBlocked_input(Boolean.TRUE).setChannel(request.getString("channel")).setModule_id(request.getString("module_id")).
                    setMessage(response).setSuggested_replies(suggestedRep);
        }catch (Exception e){
            e.printStackTrace();
            return new WebhookDto();
        }
    }

    @PostMapping("/bot/get/conf")
    public WebhookDto getRegisteredManufacturerConf(@RequestParam Map<String,String> requestBody ){
        try{
            JSONObject request = new JSONObject(requestBody);
            String phone = request.getString("phone");
            Manufacturer m = manufacturerRepository.getByPhone(phone);
            List<String> suggestedRep;
            String response = m!= null ? "Es usted \n" : "Parece que usted no esta registrado en el sistema";
            if(m != null) {
                response += "Nombres: " + (m.getNames() != null ? m.getNames() : "") + "\n";
                response += "Apellidos: " + (m.getLastNames() != null ? m.getLastNames() : "") + "\n";
                response += "Tipo de documento: " + (m.getIdType() != null ? m.getIdType() : "") + "\n";
                response += "Numero documento: " + (m.getIdNumber() != null ? m.getIdNumber() : "") + "\n";
                response+= "\n\nContinuar?";
                suggestedRep = Arrays.asList("si","no");
            }else{
                response+= "\n\nRegistrarse?";
                suggestedRep = Arrays.asList("realizar registro");
            }

            return new WebhookDto().setUser_id(request.getString("user_id")).setBot_id(request.getString("bot_id")).
                    setBlocked_input(Boolean.TRUE).setChannel(request.getString("channel")).setModule_id(request.getString("module_id")).
                    setMessage(response).setSuggested_replies(suggestedRep);
        }catch (Exception e){
            e.printStackTrace();
            return new WebhookDto();
        }
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
