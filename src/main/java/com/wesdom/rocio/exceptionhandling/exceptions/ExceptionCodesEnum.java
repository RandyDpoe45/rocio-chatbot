/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.exceptionhandling.exceptions;

import lombok.Getter;

/**
 *
 * @author randy
 */
@Getter
public enum ExceptionCodesEnum {
    
    UNEXPECTED_ERROR("001"),
    PRODUCT_EXISTING_REFERENCE("002"),
    CSV_LOAD_FAILURE("003"),
    UNPARSEABLE_JSON("004"),
    GROUP_WITH_REQUEST("005"),
    EXPERT_WITH_GROUPS("006");
    
    private String code;
    
    private ExceptionCodesEnum (String code){
        this.code = code;
    }
    
}
