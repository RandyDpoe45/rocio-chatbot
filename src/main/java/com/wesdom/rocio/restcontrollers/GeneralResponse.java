/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.restcontrollers;

import com.wesdom.rocio.exceptionhandling.exceptions.GeneralException;
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
public class GeneralResponse {
    
    private String response;
    private String errorCode;
    private String errorMessage;

    public GeneralResponse(String errorMessage,String errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
}
