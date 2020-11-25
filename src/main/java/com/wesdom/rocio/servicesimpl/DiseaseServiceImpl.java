package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.database.repositories.DiseaseRepository;
import com.wesdom.rocio.database.repositories.ProductRepository;
import com.wesdom.rocio.model.Disease;
import com.wesdom.rocio.model.Product;
import com.wesdom.rocio.services.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Disease create(Disease disease) {
        List<Long> ids = disease.getProducts().stream().map(x-> x.getId()).collect(Collectors.toList());
        List<Product> products = productRepository.getAllById(ids);
        disease.setProducts(products);
        return diseaseRepository.create(disease);
    }

    @Override
    public Disease update(Long id, Disease disease) {
        List<Long> ids = disease.getProducts().stream().map(x-> x.getId()).collect(Collectors.toList());
        List<Product> products = productRepository.getAllById(ids);
        disease.setProducts(products);
        return diseaseRepository.update(id,disease);
    }
}
