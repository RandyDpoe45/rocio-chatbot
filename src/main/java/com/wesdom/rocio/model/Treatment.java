/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.rocio.views.DiagnosisViews;
import com.wesdom.rocio.views.DiseaseViews;
import com.wesdom.rocio.views.TreatmentViews;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            DiseaseViews.CreateUpdateView.class, DiseaseViews.BasicView.class,
            DiagnosisViews.BasicView.class
    })
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date modificationDate;
    
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

    @PrePersist
    protected void prePersist() throws ParseException {
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        if (this.modificationDate == null) modificationDate = sdf.parse(sdf.format(new Date()));
    }

    @PreUpdate
    protected void preUpdate() throws ParseException {
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        if (this.modificationDate == null) modificationDate = sdf.parse(sdf.format(new Date()));
    }
        
}
