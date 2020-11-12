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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
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
            Path<T> path = buildPath(root, property);
            if(path.getJavaType().equals(Date.class)){
                return builder.equal(path.as((Date.class)), processValue(path,value));
            }else if(path.getJavaType().equals(Boolean.class)){
                return builder.equal(path.as((boolean.class)), processValue(path,value));
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
            p = p.get(s);
        }
        return p;
    }
    
    private Object processValue(Path<T> property,String value){
        if(property.getJavaType().equals(Date.class)){
            try {
                SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
                return sdf.parse(value);
            } catch (ParseException ex) {
                Logger.getLogger(PredicateBuilderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(property.getJavaType().equals(Boolean.class)){
            return Boolean.parseBoolean(value);
        }
        return value;
    }

}
