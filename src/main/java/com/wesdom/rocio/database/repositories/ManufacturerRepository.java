package com.wesdom.rocio.database.repositories;

import com.wesdom.rocio.model.Manufacturer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ManufacturerRepository {
    Manufacturer get(Long id);
    Manufacturer create(Manufacturer manufacturer);
    Manufacturer update(Long id, Manufacturer manufacturer);
    void delete(Long id);
    Page<Manufacturer> getAll(Map<String,String> queryParams);
    Manufacturer getByPhone(String phone);
}
