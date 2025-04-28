/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.exception;

import javax.ws.rs.ext.ExceptionMapper;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author Supul
 */
public class BookNotFoundExceptionMapper implements ExceptionMapper<BookNotFoundException>{
    private static final Logger logger = LoggerFactory.getLogger(BookNotFoundExceptionMapper.class);
    
    @Override
    public Response toResponse(BookNotFoundException exception){
        
        logger.error("Book not found: {}", exception.getMessage(), exception);
        
        return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
    }
}
