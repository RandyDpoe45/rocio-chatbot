/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.rocio.views.DiagnosisViews;
import com.wesdom.rocio.views.RequestViews;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Request {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({
        RequestViews.CreateUpdateView.class,RequestViews.BasicView.class,
        DiagnosisViews.CreateUpdateView.class,DiagnosisViews.BasicView.class
    })
    private Long id;
    
    @ManyToOne(targetEntity = DiagnosisGroup.class)
    @JsonView({
        RequestViews.CreateUpdateView.class, RequestViews.BasicView.class})
    private DiagnosisGroup group;

    @ManyToOne(targetEntity = Manufacturer.class)
    @JsonView({
            RequestViews.CreateUpdateView.class, RequestViews.BasicView.class})
    private Manufacturer manufacturer;

    @ManyToOne(targetEntity = Plantation.class)
    @JsonView({
            RequestViews.CreateUpdateView.class, RequestViews.BasicView.class})
    private Plantation plantation;

    @JsonView({RequestViews.CreateUpdateView.class, RequestViews.BasicView.class})
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date requestDate;
    
    @JsonView({RequestViews.CreateUpdateView.class,RequestViews.BasicView.class})
    private String email;
    @JsonView({RequestViews.CreateUpdateView.class,RequestViews.BasicView.class})
    private String  celNumber;
    @Column(length = Integer.MAX_VALUE)
    @JsonView({RequestViews.CreateUpdateView.class,RequestViews.BasicView.class})
    private String description;
    @JsonView({RequestViews.CreateUpdateView.class,RequestViews.BasicView.class})
    private String status;
    @JsonView({RequestViews.CreateUpdateView.class,RequestViews.BasicView.class})
    private String product;
    @JsonView({RequestViews.CreateUpdateView.class,RequestViews.BasicView.class})
    private String variety;
    @JsonView({RequestViews.CreateUpdateView.class,RequestViews.BasicView.class})
    private String diseaseTime;
    @JsonView({RequestViews.CreateUpdateView.class,RequestViews.BasicView.class})
    private String amountOfPlants;


    
    
}
