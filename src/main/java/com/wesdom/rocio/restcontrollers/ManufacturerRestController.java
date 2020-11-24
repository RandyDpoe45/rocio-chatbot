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
//                setCommitment(data.getDouble("commitment")).
        setPhone(data.getString("phone")).setRole(data.getString("role"));
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
            String response = "Usuario creado con la siguiente informacion\n";
            response += "Nombres: "+m.getNames()+"\n";
            response += "Apellidos: "+m.getLastNames()+"\n";
            response += "Rol: "+m.getRole()+"\n";
            response += "Celular: "+m.getPhone()+"\n\n Continuar?";
            List<String> suggestedRep = Arrays.asList("si");
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
