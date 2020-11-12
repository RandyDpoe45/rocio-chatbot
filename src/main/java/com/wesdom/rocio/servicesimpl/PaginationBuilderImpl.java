/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.servicesimpl;

import com.wesdom.rocio.model.enums.PaginationParamsEnum;
import com.wesdom.rocio.services.IPaginationBuilder;
import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 *
 * @author randy
 */
public class PaginationBuilderImpl implements IPaginationBuilder{

    @Override
    public Pageable createPagination(Map<String, String> params) {
        String pageSize = params.get(PaginationParamsEnum.PAGE_SIZE.getTag());
        String pageNumber = params.get(PaginationParamsEnum.PAGE_NUMBER.getTag());
        if(pageNumber != null && pageSize != null){
            String sortDir = params.get(PaginationParamsEnum.SORT_DIRECTION.getTag());
            String sortProp = params.get(PaginationParamsEnum.SORT_PROPERTY.getTag());
            if(sortProp != null){
                if(sortDir != null){
                    Direction direction =  sortProp.equals(PaginationParamsEnum.SORT_DIRECTION.getDefaultValue()) ? 
                            Sort.Direction.DESC : Sort.Direction.ASC;
                    return PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize), Sort.by(direction, sortProp));
                }
                return PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize), Sort.by(sortProp));
            }
            return PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
        }
        return Pageable.unpaged();
    }
    
}
