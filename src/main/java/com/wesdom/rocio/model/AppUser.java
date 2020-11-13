/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.rocio.views.DiagnosisGroupViews;
import com.wesdom.rocio.views.ExpertViews;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
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
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AppUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({
        DiagnosisGroupViews.CreateUpdateView.class, DiagnosisGroupViews.BasicView.class,
        ExpertViews.CreateUpdateView.class, ExpertViews.BasicView.class
    })
    private Long id;
    
    @JsonView({
        DiagnosisGroupViews.BasicView.class,
        ExpertViews.CreateUpdateView.class, ExpertViews.BasicView.class
    })
    private String userId;
    
    @JsonView({
        ExpertViews.CreateUpdateView.class, ExpertViews.BasicView.class
    })
    private Double effectiveness; 
    
    @ManyToMany(targetEntity = KnowledgeArea.class)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonView({
        ExpertViews.CreateUpdateView.class, ExpertViews.BasicView.class
    })
    private List<KnowledgeArea> knowledgeAreas;
    
    @JsonView({
        ExpertViews.CreateUpdateView.class, ExpertViews.BasicView.class
    })
    private Long diagnosisAsig;
    
    
    
}
