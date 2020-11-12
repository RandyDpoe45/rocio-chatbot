/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.services;

import java.util.Map;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author randy
 */
public interface IPredicateBuilder<T> {
    
    Specification<T> createPredicate(Map<String,String> params);
}
