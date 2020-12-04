package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.PlantationJpaRepository;
import com.wesdom.rocio.database.repositories.PlantationRepository;
import com.wesdom.rocio.model.Plantation;
import com.wesdom.rocio.services.IPaginationBuilder;
import com.wesdom.rocio.services.IPredicateBuilder;
import com.wesdom.rocio.servicesimpl.PaginationBuilderImpl;
import com.wesdom.rocio.servicesimpl.PredicateBuilderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PlantationRepositoryImpl implements PlantationRepository {

    @Autowired
    private PlantationJpaRepository plantationJpaRepository;

    @Override
    public Plantation get(Long id) {
        return plantationJpaRepository.getOne(id);
    }

    @Override
    public Plantation create(Plantation plantation) {
        return plantationJpaRepository.save(plantation);
    }

    @Override
    public Plantation update(Long id, Plantation plantation) {
        Plantation p = plantationJpaRepository.getOne(id);
        p.setManufacturer(plantation.getManufacturer()).setAddress(plantation.getAddress()).setDepartment(plantation.getDepartment()).
                setMunicipality(plantation.getMunicipality()).setName(plantation.getName());
        return plantationJpaRepository.save(p);
    }

    @Override
    public void delete(Long id) {
        plantationJpaRepository.deleteById(id);
    }

    @Override
    public Page<Plantation> getAll(Map<String, String> queryParams) {
        IPredicateBuilder<Plantation> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder pagination = new PaginationBuilderImpl();
        return plantationJpaRepository.findAll(predicate.createPredicate(queryParams),pagination.createPagination(queryParams));
    }
}
