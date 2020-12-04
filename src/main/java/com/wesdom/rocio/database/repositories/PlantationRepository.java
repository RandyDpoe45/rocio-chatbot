package com.wesdom.rocio.database.repositories;

import com.wesdom.rocio.model.Plantation;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface PlantationRepository {
    Plantation get(Long id);
    Plantation create(Plantation plantation);
    Plantation update(Long id, Plantation plantation);
    void delete(Long id);
    Page<Plantation> getAll(Map<String,String> queryParams);
    
}
