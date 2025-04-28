/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.exception;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author Supul
 */
public class InvalidInputExceptionMapper implements ExceptionMapper<InvalidInputException>{
    private static final Logger logger = LoggerFactory.getLogger(InvalidInputExceptionMapper.class);
    
    @Override 
    public Response toResponse(InvalidInputException exception){
        logger.error("Invalid input: {}", exception.getMessage(), exception);
        
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
