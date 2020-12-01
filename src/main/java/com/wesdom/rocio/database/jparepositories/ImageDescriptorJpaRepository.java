package com.wesdom.rocio.database.jparepositories;

import com.wesdom.rocio.model.ImageDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDescriptorJpaRepository extends JpaRepository<ImageDescriptor,Long> , JpaSpecificationExecutor<ImageDescriptor> {
}
