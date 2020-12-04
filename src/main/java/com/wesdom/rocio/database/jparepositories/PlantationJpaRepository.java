package com.wesdom.rocio.database.jparepositories;

import com.wesdom.rocio.model.Plantation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantationJpaRepository extends JpaRepository<Plantation,Long> , JpaSpecificationExecutor<Plantation> {
}
