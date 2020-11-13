/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.model;

import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author randy
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@MappedSuperclass
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double effectiveness; 
    
    @ManyToMany(targetEntity = KnowledgeArea.class)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<KnowledgeArea> knowledgeAreas;
    
    private Long diagnosisAsig;
    
    
    
}