/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.rocio.views.DiseaseViews;
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
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({
        DiseaseViews.CreateUpdateView.class, DiseaseViews.BasicView.class
    })
    private Long id;
    
    @JsonView({
        DiseaseViews.CreateUpdateView.class, DiseaseViews.BasicView.class
    })
    private String name;
    
}
