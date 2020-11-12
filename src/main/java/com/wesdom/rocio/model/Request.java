/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.model;

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
    private Long id;
    
    @ManyToOne(targetEntity = Group.class)
    private Group group;
    
    private String email;
    
    private String  celNumber;
    
    private String description;
    
    private String status;
    
    private String product;
    
    private String variety;
    
    private String diseaseTime;
    
    private String amountOfPlants;
    
    
}
