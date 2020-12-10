/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.jparepositories;

import com.wesdom.rocio.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author randy
 */
@Repository
public interface RequestJpaRepository extends JpaRepository<Request, Long>, JpaSpecificationExecutor<Request> {

    Request findTop1ByGroupId(Long diagnosisGroupId);
}
