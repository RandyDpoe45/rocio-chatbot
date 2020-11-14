/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.jparepositories;

import com.wesdom.rocio.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author randy
 */
@Repository
public interface DiagnosisJpaRepository extends JpaRepository<Diagnosis, Long>, JpaSpecificationExecutor<Diagnosis> {

    @Query("SELECT d FROM Diagnosis d WHERE d.request.id = ?2 AND d.user.id IN ?1")
    List<Diagnosis> getByUserIdAndRequestId(List<Long> userId, Long requestId);
}
