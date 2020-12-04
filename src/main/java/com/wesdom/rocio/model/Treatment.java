/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.rocio.views.DiagnosisViews;
import com.wesdom.rocio.views.DiseaseViews;
import com.wesdom.rocio.views.TreatmentViews;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Treatment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({
        TreatmentViews.CreateUpdateView.class,TreatmentViews.BasicView.class,
        DiagnosisViews.CreateUpdateView.class,DiagnosisViews.BasicView.class,
        DiseaseViews.CreateUpdateView.class,DiseaseViews.BasicView.class
    })
    private Long id;
    
    @JsonView({
        TreatmentViews.CreateUpdateView.class,TreatmentViews.BasicView.class,
        DiseaseViews.BasicView.class,
        DiagnosisViews.BasicView.class
    })
    private String type;

    @JsonView({
            TreatmentViews.CreateUpdateView.class,TreatmentViews.BasicView.class,
            DiseaseViews.BasicView.class,
            DiagnosisViews.BasicView.class
    })
    private String name;
    
    @JsonView({
        TreatmentViews.CreateUpdateView.class,TreatmentViews.BasicView.class,
        DiseaseViews.BasicView.class,
        DiagnosisViews.BasicView.class
    })
    @Column(length = Integer.MAX_VALUE)
    private String preparation;
    
    @JsonView({
        TreatmentViews.CreateUpdateView.class,TreatmentViews.BasicView.class,
        DiseaseViews.BasicView.class
    })
    private Boolean biological;
        
}
