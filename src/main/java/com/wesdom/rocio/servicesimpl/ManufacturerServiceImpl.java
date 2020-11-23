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
        return manufacturerRepository.create(manufacturer);
    }

    @Override
    public Manufacturer update(Long id, Manufacturer manufacturer) {
        return manufacturerRepository.update(id,manufacturer);
    }
}
