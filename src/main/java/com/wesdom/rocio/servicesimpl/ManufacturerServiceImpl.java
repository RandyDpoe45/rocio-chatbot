package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.database.repositories.ManufacturerRepository;
import com.wesdom.rocio.model.Manufacturer;
import com.wesdom.rocio.services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        Manufacturer m = manufacturerRepository.getByPhone(manufacturer.getPhone());
        if(m == null){
            return manufacturerRepository.create(manufacturer);
        }
        return manufacturer;
    }

    @Override
    public Manufacturer update(Long id, Manufacturer manufacturer) {
        Manufacturer m = manufacturerRepository.getByPhone(manufacturer.getPhone());
        if(m != null){
            return manufacturerRepository.update(id,manufacturer);
        }
        return manufacturer;
    }
}
