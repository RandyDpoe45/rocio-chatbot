/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.database.jparepositories;

import com.wesdom.rocio.model.KnowledgeArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author randy
 */
@Repository
public interface KnowledgeAreaJpaRepository extends JpaRepository<KnowledgeArea, Long>, JpaSpecificationExecutor<KnowledgeArea> {
    
}
