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
import com.wesdom.rocio.views.RequestViews;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author randy
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class DiagnosisGroup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({RequestViews.CreateUpdateView.class,RequestViews.BasicView.class,
        DiagnosisGroupViews.CreateUpdateView.class, DiagnosisGroupViews.BasicView.class})
    private Long id;
    
    @JsonView({RequestViews.BasicView.class,
        DiagnosisGroupViews.CreateUpdateView.class, DiagnosisGroupViews.BasicView.class})
    private String name;

    @ManyToMany(targetEntity = KnowledgeArea.class)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonView({
            DiagnosisGroupViews.CreateUpdateView.class, DiagnosisGroupViews.BasicView.class
    })
    private List<KnowledgeArea> knowledgeAreas;

    @ManyToOne(targetEntity = Expert.class)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonView({
        DiagnosisGroupViews.CreateUpdateView.class, DiagnosisGroupViews.BasicView.class
    })
    private Expert expert;

    @ManyToMany(targetEntity = Apprentice.class)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonView({
        DiagnosisGroupViews.CreateUpdateView.class, DiagnosisGroupViews.BasicView.class
    })
    private List<Apprentice> apprentices;

    @JsonView({
        DiagnosisGroupViews.CreateUpdateView.class, DiagnosisGroupViews.BasicView.class
    })
    private Integer minimumResponses;

//    private Long amountEPRequest;

//    private Long amountPDRequest;
//
//    private Long amountAMRequest;
//
//    private Long amountAARequest;
//
//    private Long amountEEquest;
    
}
