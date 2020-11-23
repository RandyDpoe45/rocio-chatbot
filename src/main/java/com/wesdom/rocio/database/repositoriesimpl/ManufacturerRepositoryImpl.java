package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.ManufacturerJpaRepository;
import com.wesdom.rocio.database.repositories.ManufacturerRepository;
import com.wesdom.rocio.model.KnowledgeArea;
import com.wesdom.rocio.model.Manufacturer;
import com.wesdom.rocio.services.IPaginationBuilder;
import com.wesdom.rocio.services.IPredicateBuilder;
import com.wesdom.rocio.servicesimpl.PaginationBuilderImpl;
import com.wesdom.rocio.servicesimpl.PredicateBuilderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ManufacturerRepositoryImpl implements ManufacturerRepository {

    @Autowired
    private ManufacturerJpaRepository manufacturerJpaRepository;

    @Override
    public Manufacturer get(Long id) {
        return manufacturerJpaRepository.getOne(id);
    }

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return manufacturerJpaRepository.save(manufacturer);
    }

    @Override
    public Manufacturer update(Long id, Manufacturer manufacturer) {
        Manufacturer m = manufacturerJpaRepository.getOne(id);
        m.setPhone(manufacturer.getPhone()).setCommitment(manufacturer.getCommitment()).setRole(manufacturer.getRole());
        return manufacturerJpaRepository.save(m);
    }

    @Override
    public void delete(Long id) {
        manufacturerJpaRepository.deleteById(id);
    }

    @Override
    public Page<Manufacturer> getAll(Map<String, String> queryParams) {
        IPredicateBuilder<Manufacturer> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder pagination = new PaginationBuilderImpl();
        return manufacturerJpaRepository.findAll(predicate.createPredicate(queryParams),pagination.createPagination(queryParams));
    }

    @Override
    public Manufacturer getByPhone(String phone) {
        return manufacturerJpaRepository.findTop1ByPhone(phone);
    }
}