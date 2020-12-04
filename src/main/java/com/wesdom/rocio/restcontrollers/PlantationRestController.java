package com.wesdom.rocio.restcontrollers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesdom.rocio.database.repositories.ManufacturerRepository;
import com.wesdom.rocio.database.repositories.PlantationRepository;
import com.wesdom.rocio.model.Manufacturer;
import com.wesdom.rocio.model.Request;
import com.wesdom.rocio.model.Plantation;
import com.wesdom.rocio.views.RequestViews;
import com.wesdom.rocio.views.PlantationViews;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("rocio/v1/plantation")
public class PlantationRestController {

    @Autowired
    private PlantationRepository plantationRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @PostMapping("/bot")
    @JsonView(RequestViews.BasicView.class)
    public Plantation createFromBot(@RequestBody String chatBotData) {
        try {
            JSONObject data = new JSONObject(chatBotData).getJSONObject("variables");
            System.out.println("!!!data: "+data.toString());
            Manufacturer m = manufacturerRepository.getByPhone(data.getString("reg_finca_manPhone"));
            Plantation r = new Plantation().setManufacturer(m).setAddress(data.getString("reg_finca_address")).
                    setDepartment(data.getString("reg_finca_department")).setName(data.getString("reg_finca_name")).
                    setMunicipality(data.getString("reg_finca_municipality"));
            r = plantationRepository.create(r);

            return r;
        } catch (Exception e) {
            e.printStackTrace();
            return new Plantation();
        }
    }


    @GetMapping
    @JsonView(PlantationViews.BasicView.class)
    public Page<Plantation> getAll(@RequestParam Map<String, String> queryParams) {
        return plantationRepository.getAll(queryParams);
    }

    @PostMapping("")
    @JsonView(PlantationViews.BasicView.class)
    public Plantation create(@RequestBody String data) throws JsonProcessingException {
        Plantation diagnosisGroup = decode(data);
        return plantationRepository.create(diagnosisGroup);
    }

    @PutMapping("/{id}")
    @JsonView(PlantationViews.BasicView.class)
    public Plantation update(@PathVariable Long id, @RequestBody String data) throws JsonProcessingException {
        Plantation r = decode(data);
        r = plantationRepository.update(id, r);
        return r;
    }

    @DeleteMapping("/{id}")
    public GeneralResponse delete(@PathVariable Long id) {
        plantationRepository.delete(id);
        return new GeneralResponse().setErrorCode("000").setResponse("Borado con exito");
    }

    private Plantation decode(String data) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return o.readerWithView(PlantationViews.CreateUpdateView.class)
                .forType(Plantation.class)
                .readValue(data);
    }
}
