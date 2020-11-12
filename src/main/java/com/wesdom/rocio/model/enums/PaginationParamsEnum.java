/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.model.enums;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 *
 * @author randy
 */
@Getter
@Accessors(chain = true)
public enum PaginationParamsEnum {
    
    PAGE_SIZE("20","pSize"),
    PAGE_NUMBER("0","pNumber"),
    SORT_DIRECTION("desc","sDirection"),
    SORT_PROPERTY("id","sProperty");
    
    private final String defaultValue;
    private final String tag;

    private PaginationParamsEnum(String defaultValue,String tag){
        this.defaultValue = defaultValue;
        this.tag = tag;
    }
    
    
}
