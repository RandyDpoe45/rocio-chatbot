/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.jparepositories;

import com.wesdom.rocio.model.DiagnosisGroup;
import org.springframework.data.domain.Pageable;
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
public interface GroupJpaRepository extends JpaRepository<DiagnosisGroup, Long>, JpaSpecificationExecutor<DiagnosisGroup> {

    @Query("SELECT dg, COALESCE((SELECT COUNT(r) FROM Request r WHERE r.status IN ('EP','EE','PD') AND r.group = dg),0) AS numberOfRequest " +
            "FROM DiagnosisGroup dg GROUP BY dg ORDER BY numberOfRequest ASC")
    List<Object[]> findTop1OrderByNumberOfRequestDesc(Pageable pageable);
}
