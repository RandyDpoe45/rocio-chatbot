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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

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
@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Disease {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({
        DiseaseViews.CreateUpdateView.class, DiseaseViews.BasicView.class,
        DiagnosisViews.CreateUpdateView.class,DiagnosisViews.BasicView.class
    })
    private Long id;
    
    @JsonView({
        DiseaseViews.CreateUpdateView.class, DiseaseViews.BasicView.class,
        DiagnosisViews.BasicView.class
    })
    @Column(length = Integer.MAX_VALUE)
    private String name;
    
    @JsonView({
        DiseaseViews.CreateUpdateView.class, DiseaseViews.BasicView.class,
        DiagnosisViews.BasicView.class
    })
    @Column(length = Integer.MAX_VALUE)
    private String scientificName;

    @JsonView({
            DiseaseViews.CreateUpdateView.class, DiseaseViews.BasicView.class,
            DiagnosisViews.BasicView.class
    })
    @Column(length = Integer.MAX_VALUE)
    private String image;

    @JsonView({
            DiseaseViews.CreateUpdateView.class, DiseaseViews.BasicView.class,
            DiagnosisViews.BasicView.class
    })
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date modificationDate;

    @JsonView({
        DiseaseViews.CreateUpdateView.class, DiseaseViews.BasicView.class
    })
    @ManyToMany(targetEntity = Product.class)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Product> products;
    
    @JsonView({
        DiseaseViews.CreateUpdateView.class, DiseaseViews.BasicView.class
    })
    @ManyToMany(targetEntity = Treatment.class)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Treatment> treatments;

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
