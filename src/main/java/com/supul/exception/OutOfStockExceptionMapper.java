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
public class OutOfStockExceptionMapper implements ExceptionMapper<OutOfStockException>{
    private static final Logger logger = LoggerFactory.getLogger(OutOfStockExceptionMapper.class);
    
    @Override 
    public Response toResponse(OutOfStockException exception){
        logger.error("Book out of stock: {}", exception.getMessage(), exception);
        
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
