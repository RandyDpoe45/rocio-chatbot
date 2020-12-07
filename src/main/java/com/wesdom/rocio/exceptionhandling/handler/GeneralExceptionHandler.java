/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.exceptionhandling.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesdom.rocio.exceptionhandling.exceptions.ExceptionCodesEnum;
import com.wesdom.rocio.exceptionhandling.exceptions.GeneralException;
import com.wesdom.rocio.restcontrollers.GeneralResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author randy
 */
@RestControllerAdvice
public class GeneralExceptionHandler {
    
    @ExceptionHandler
    @ResponseBody
    public GeneralResponse handlerGeneralException(GeneralException exception){
        return new GeneralResponse(exception.getMessage(),exception.getExceptionCode());
    }
    
    @ExceptionHandler
    @ResponseBody
    public GeneralResponse handlerJsonProcessingException(JsonProcessingException exception){
        exception.printStackTrace();
        return new GeneralResponse("Json mal formado", ExceptionCodesEnum.UNPARSEABLE_JSON.getCode());
    }
    
    @ExceptionHandler
    @ResponseBody
    public GeneralResponse HandlerException(Exception exception){
        exception.printStackTrace();
        return new GeneralResponse("Error inesperado",ExceptionCodesEnum.UNEXPECTED_ERROR.getCode());
    }
    
}
