/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.rocio.views.ApprenticeViews;
import com.wesdom.rocio.views.DiagnosisGroupViews;
import com.wesdom.rocio.views.ExpertViews;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wesdom.rocio.views.KnowledgeAreaViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 *
 * @author randy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class KnowledgeArea {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({
        KnowledgeAreaViews.CreateUpdateView.class, KnowledgeAreaViews.BasicView.class,
        DiagnosisGroupViews.CreateUpdateView.class, DiagnosisGroupViews.BasicView.class
    })
    private Long id;
    
    @JsonView({
        KnowledgeAreaViews.CreateUpdateView.class, KnowledgeAreaViews.BasicView.class,
        DiagnosisGroupViews.BasicView.class
    })
    private String name;

    @JsonView({
        KnowledgeAreaViews.CreateUpdateView.class, KnowledgeAreaViews.BasicView.class,
    })
    private String description;

    
}
