package com.wesdom.rocio.restcontrollers;

import com.wesdom.rocio.database.repositories.ImageDescriptorRepository;
import com.wesdom.rocio.model.ImageDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("rocio/v1/imageDescriptor")
public class ImageDescriptorRestController {

    @Autowired
    private ImageDescriptorRepository imageDescriptorRepository;


    @GetMapping
    public Page<ImageDescriptor> getAll(@RequestParam Map<String,String> params){
        return imageDescriptorRepository.getAll(params);
    }

}
