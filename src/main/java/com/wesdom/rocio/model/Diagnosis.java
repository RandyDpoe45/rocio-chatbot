/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.rocio.views.DiagnosisViews;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Diagnosis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({
        DiagnosisViews.CreateUpdateView.class,DiagnosisViews.BasicView.class
    })
    private Long id;
    
    @ManyToOne(targetEntity = Request.class)
    @JsonView({
        DiagnosisViews.CreateUpdateView.class,DiagnosisViews.BasicView.class
    })
    private Request request;
    
    @OneToMany(targetEntity = Disease.class)
    @JsonView({
        DiagnosisViews.CreateUpdateView.class,DiagnosisViews.BasicView.class
    })
    private List<Disease> diseases;
    
    @OneToMany(targetEntity = Treatment.class)
    @JsonView({
        DiagnosisViews.CreateUpdateView.class,DiagnosisViews.BasicView.class
    })
    private List<Treatment> treatments;
    
    @ManyToOne(targetEntity = AppUser.class)
    @JsonView({
        DiagnosisViews.CreateUpdateView.class,DiagnosisViews.BasicView.class
    })
    private AppUser user;

    @JsonView({
        DiagnosisViews.CreateUpdateView.class,DiagnosisViews.BasicView.class
    })
    private String comments;
    
}
