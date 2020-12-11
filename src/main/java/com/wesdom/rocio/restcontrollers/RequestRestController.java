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
import com.wesdom.rocio.database.repositories.ImageDescriptorRepository;
import com.wesdom.rocio.database.repositories.PlantationRepository;
import com.wesdom.rocio.database.repositories.RequestRepository;
import com.wesdom.rocio.dtos.WebhookDto;
import com.wesdom.rocio.model.ImageDescriptor;
import com.wesdom.rocio.model.Plantation;
import com.wesdom.rocio.model.Request;
import com.wesdom.rocio.services.RequestService;
import com.wesdom.rocio.views.RequestViews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.json.JSONArray;
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
@RequestMapping("rocio/v1/request")
public class RequestRestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ImageDescriptorRepository imageDescriptorRepository;

    @Autowired
    private PlantationRepository plantationRepository;

    @PostMapping("")
    @JsonView(RequestViews.BasicView.class)
    public Request create(@RequestBody String chatBotData) {
        try {
            JSONObject data = new JSONObject(chatBotData).getJSONObject("variables");
            System.out.println("!!!data: "+data.toString());
            Pattern p = Pattern.compile("\\#([0-9]+)\\#");
            Matcher m = p.matcher(data.getString("plantation"));
            Plantation plantation = null;
            if(m.find()){
                String s = m.group().replace("#","").trim();
                System.out.println("!!!!!idPlantation: "+s);
                plantation = plantationRepository.get(Long.parseLong(s));
            }
            Request r = new Request().setAmountOfPlants(data.getString("amountOfPlants")).setCelNumber(data.getString("celNumber")).
                    setDescription(data.getString("description")).setDiseaseTime(data.getString("diseaseTime")).
                    setProduct(data.getString("product")).setStatus("EE").setVariety(data.getString("variety")).setPlantation(plantation);

            r = requestService.create(r);
            String images = data.getString("images");
            System.out.println("!!!images "+images);
            p = Pattern.compile("(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?");
            m = p.matcher(images);
            while(m.find()){
                imageDescriptorRepository.create(new ImageDescriptor().setImageLink(m.group()).setRequest(r));
            }
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            return new Request();
        }
    }
    

    @PostMapping("/bot")
    public WebhookDto getBotSolvedIssues(@RequestParam Map<String,String> requestBody ) throws JSONException{
        try{
            JSONObject request = new JSONObject(requestBody);
            Map<String,String> filters = new HashMap<>();
            filters.put("celNumber", request.getString("celular_consulta"));
            filters.put("status","AM,AA");
            Page<Request> requests = requestRepository.getAll(filters);
            List<String> suggestedRep = requests.stream().map(x -> {return "#"+x.getId()+"#";}).collect(Collectors.toList());
            List<Object> cards = requests.stream().map(x -> {
               return new JSONObject().put("type","text").put("value","Numero de solicitud: "+x.getId()+"#\nDescripcion: "+x.getDescription())
                       .put("buttons",new JSONObject().put("type","url").put("value","https://google.com")
                               .put("name","seleccionar"))
                       .toMap();
            }).collect(Collectors.toList());
            return new WebhookDto().setUser_id(request.getString("user_id")).setBot_id(request.getString("bot_id")).
                    setBlocked_input(Boolean.TRUE).setChannel(request.getString("channel")).setModule_id(request.getString("module_id")).
                    setSuggested_replies(suggestedRep).setMessage("Estos son tus problemas").setCards(cards);
//                    .setCards(new JSONArray("[\n" +
//                    "    {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "            \"value\": \"Test Text Card\",\n" +
//                    "            \"buttons\": [\n" +
//                    "        {\n" +
//                    "            \"type\": \"url\",\n" +
//                    "                \"value\": \"https://google.com\",\n" +
//                    "                \"name\": \"google\"\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"type\": \"module\",\n" +
//                    "                \"value\": \"4600\",\n" +
//                    "                \"name\": \"Change Module\"\n" +
//                    "        }\n" +
//                    "            ]\n" +
//                    "    }\n" +
//                    "]").toList());
        }catch(Exception e){
            e.printStackTrace();
            return new WebhookDto();
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
