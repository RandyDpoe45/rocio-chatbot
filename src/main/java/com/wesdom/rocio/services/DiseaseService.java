package com.wesdom.rocio.services;

import com.wesdom.rocio.model.Disease;

public interface DiseaseService {
    Disease create(Disease disease);
    Disease update(Long id, Disease disease);
}
