/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.exceptionhandling.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 *
 * @author randy
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class GeneralException extends RuntimeException{
    
    private String exceptionCode;

    public GeneralException(ExceptionCodesEnum exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode.getCode();
    }
    
    
}
