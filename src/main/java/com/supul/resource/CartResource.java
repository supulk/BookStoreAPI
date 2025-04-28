/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.resource;

import com.supul.dao.CartDAO;
import com.supul.exception.InvalidInputException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Supul
 */
@Path("/customers/{customerId}/cart")
public class CartResource {
    private CartDAO cartDAO = new CartDAO();
    private static final Logger logger = LoggerFactory.getLogger(CartResource.class);
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCart(@PathParam("customerId") int customerId){
        if (customerId <= 0) {
            logger.error("invalid customer id provided");
            throw new InvalidInputException("invalid customer id provided");
        }
        return Response.status(Response.Status.OK)
                    .entity(cartDAO.getCart(customerId))
                    .build();
    }
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addToCart(@PathParam("customerId") int customerId, int bookId){
        if (customerId <= 0) {
            logger.error("invalid customer id provided");
            throw new InvalidInputException("invalid customer id provided");
        }
        if (bookId <= 0) {
            logger.error("invalid bookId id provided");
            throw new InvalidInputException("invalid bookId id provided");
        }
        
        cartDAO.addToCart(customerId, bookId);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{bookId}")
    public Response addToCartt(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId, int quantity){
        if (customerId <= 0) {
            logger.error("invalid customer id provided");
            throw new InvalidInputException("invalid customer id provided");
        }
        if (bookId <= 0) {
            logger.error("invalid bookId id provided");
            throw new InvalidInputException("invalid bookId id provided");
        }
        if (quantity <= 0) {
            logger.error("invalid bookId id provided");
            throw new InvalidInputException("invalid bookId id provided");
        }
        cartDAO.updateCart(customerId, bookId, quantity);
        return Response.status(Response.Status.OK).build();
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{bookId}")
    public Response deleteFromCart(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId){
        if (customerId <= 0) {
            logger.error("invalid customer id provided");
            throw new InvalidInputException("invalid customer id provided");
        }
        if (bookId <= 0) {
            logger.error("invalid bookId id provided");
            throw new InvalidInputException("invalid bookId id provided");
        }
        cartDAO.deleteFromCart(customerId, bookId);
        return Response.status(Response.Status.OK)
                    .build();
    }
    
    
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
