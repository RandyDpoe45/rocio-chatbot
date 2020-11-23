package com.wesdom.rocio.services;

import com.wesdom.rocio.model.Manufacturer;

public interface ManufacturerService {
    
    Manufacturer create(Manufacturer manufacturer);
    Manufacturer update(Long id, Manufacturer manufacturer);

}
