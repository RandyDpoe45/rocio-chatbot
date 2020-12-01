package com.wesdom.rocio.database.repositoriesimpl;

import com.wesdom.rocio.database.jparepositories.ImageDescriptorJpaRepository;
import com.wesdom.rocio.database.repositories.ImageDescriptorRepository;
import com.wesdom.rocio.model.ImageDescriptor;
import com.wesdom.rocio.services.IPaginationBuilder;
import com.wesdom.rocio.services.IPredicateBuilder;
import com.wesdom.rocio.servicesimpl.PaginationBuilderImpl;
import com.wesdom.rocio.servicesimpl.PredicateBuilderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ImageDescriptorRepositoryImpl implements ImageDescriptorRepository {

    @Autowired
    private ImageDescriptorJpaRepository imageDescriptorJpaRepository;


    @Override
    public Page<ImageDescriptor> getAll(Map<String, String> params) {
        IPredicateBuilder<ImageDescriptor> predicate = new PredicateBuilderServiceImpl<>();
        IPaginationBuilder paginationBuilder = new PaginationBuilderImpl();
        return imageDescriptorJpaRepository.findAll(predicate.createPredicate(params), paginationBuilder.createPagination(params));
    }

    @Override
    public ImageDescriptor create(ImageDescriptor imageDescriptor) {
        return imageDescriptorJpaRepository.save(imageDescriptor);
    }
}
