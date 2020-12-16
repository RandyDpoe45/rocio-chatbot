/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.model.enums.PaginationParamsEnum;
import com.wesdom.rocio.services.IPredicateBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.MapAttribute;

import org.hibernate.query.criteria.internal.path.RootImpl;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author randy
 */
public class PredicateBuilderServiceImpl<T> implements IPredicateBuilder<T> {

    /**
     * Method to create dynamic predicates for query using specification jpa
     * API.
     * @param params
     */
    @Override
    public Specification<T> createPredicate(Map<String, String> params) {
        boolean index = false;
        List<String> paginationParams = Arrays.asList(PaginationParamsEnum.values()).
                stream().map(x -> x.getTag()).collect(Collectors.toList());
        Specification<T> specifications = null;
        for (String k : params.keySet()) {
            //Omits pagination paramas
            if (!paginationParams.contains(k)) {
                if (index) {
                    specifications = specifications.and(createSpecification(k, params.get(k)));
                } else {
                    specifications = Specification.where(createSpecification(k, params.get(k)));
                    index = true;
                }
            }
        }

        return specifications;
    }

    /**
     * Specification builder.
     */
    private Specification<T> createSpecification(String property, String value) {
        
        return (root, query, builder) -> {
            Expression<T> path = buildPath(root, property);
            
            if(path.getJavaType().equals(Date.class)){
                if(value.contains(",")){
                    return path.as(Date.class).in(processValue(path,value));
                }
                return builder.equal(path.as((Date.class)), processValue(path,value));
            }else if(path.getJavaType().equals(Boolean.class)){
                if(value.contains(",")){
                    return path.as(Boolean.class).in(processValue(path,value));
                }
                return builder.equal(path.as((Boolean.class)), processValue(path,value));
            }
            if(value.contains(",")){
                return path.as(String.class).in(processValue(path,value));
            }
            return builder.equal(path.as((String.class)), processValue(path,value));
        };
    }

    /**
     * Method to create Path to access nested properties in entity.
     */
    private Path<T> buildPath(Root<T> root, String property) {
        Path<T> p = root;
        for (String s : property.split("\\.")) {
            if(Collection.class.isAssignableFrom(p.get(s).getJavaType())){
                p = ((From) p).join(s);
            }else {
                p = p.get(s);
            }
        }
        return p;
    }
    
    private Object processValue(Expression<T> property,String value){
        if(property.getJavaType().equals(Date.class)){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if(value.contains(",")){
                return Arrays.asList(value.split(",")).stream().map(x -> {
                    try {
                        return sdf.parse(x);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());
            }else {
                try {
                    return sdf.parse(value);
                } catch (ParseException ex) {
                    Logger.getLogger(PredicateBuilderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(property.getJavaType().equals(Boolean.class)){
            if(value.contains(",")){
                return Arrays.asList(value.split(",")).stream().map(x -> Boolean.parseBoolean(x)).collect(Collectors.toList());
            }
            return Boolean.parseBoolean(value);
        }
        if(value.contains(",")){
            return Arrays.asList(value.split(","));
        }
        return value;
    }

}
