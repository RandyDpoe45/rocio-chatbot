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
@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Disease {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({
        
        DiagnosisViews.CreateUpdateView.class,DiagnosisViews.BasicView.class
    })
    private Long id;
    
    @JsonView({
        
        DiagnosisViews.BasicView.class
    })
    private String name;
    
    @JsonView({
        
        DiagnosisViews.BasicView.class
    })
    private String scientificName;
    
    @ManyToMany(targetEntity = Product.class)
    private List<Product> products;
    
    @ManyToMany(targetEntity = Treatment.class)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Treatment> treatments;
    
}
