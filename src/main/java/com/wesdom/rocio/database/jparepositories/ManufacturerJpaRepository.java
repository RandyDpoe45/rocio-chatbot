package com.wesdom.rocio.database.jparepositories;

import com.wesdom.rocio.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerJpaRepository extends JpaRepository<Manufacturer,Long>, JpaSpecificationExecutor<Manufacturer> {

    Manufacturer findTop1ByPhone(String phone);
}
