/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.jparepositories;

import com.wesdom.rocio.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author randy
 */
@Repository
public interface ExpertJpaRepository extends JpaRepository<Expert, Long>, JpaSpecificationExecutor<Expert> {

    @Query("SELECT count(dg) FROM DiagnosisGroup dg WHERE dg.expert.id = ?1")
    Long getAmountOfDiagnosisGroups(Long id);
}
