/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.rocio.views.DiagnosisViews;
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
        
        DiagnosisViews.CreateUpdateView.class,DiagnosisViews.BasicView.class
    })
    private Long id;
    
    @JsonView({
        
        DiagnosisViews.BasicView.class
    })
    private String type;
    
    @JsonView({
        
        DiagnosisViews.BasicView.class
    })
    private String preparation;
    
    private Boolean biological;
        
}
