/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.resource;

import com.supul.dao.OrderDAO;
import com.supul.exception.InvalidInputException;
import com.supul.model.Order;
import com.supul.model.Book;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("/customers/{customerId}/orders")
public class OrderResource {
    private OrderDAO orderDAO = new OrderDAO(); 
    private static final Logger logger = LoggerFactory.getLogger(OrderResource.class);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders(@PathParam("customerId") int id){
        if (id <= 0) {
            logger.error("invalid customer id provided");
            throw new InvalidInputException("invalid customer id provided");
        }
        return Response.status(Response.Status.OK)
                    .entity(orderDAO.getAllOrders(id))
                    .build();
    }
    
    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("customerId") int customerID,@PathParam("orderId") int orderId){
        if (customerID <= 0) {
            logger.error("invalid customer id provided");
            throw new InvalidInputException("invalid customer id provided");
        }
        if (orderId <= 0) {
            logger.error("invalid order id provided");
            throw new InvalidInputException("invalid order id provided");
        }
        return Response.status(Response.Status.OK)
                    .entity(orderDAO.getOrderById(customerID, orderId))
                    .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(@PathParam("customerId") int customerID, OrderRequest request){
        if (request == null || request.getOrderItems() == null || request.getOrderItems().isEmpty()) {
            logger.error("Order items not provided");
            throw new InvalidInputException("Order items not provided");
        }
        Map<Integer, Integer> orderItems = request.getOrderItems();
        if (customerID <= 0) {
            logger.error("invalid customer id provided");
            throw new InvalidInputException("invalid customer id provided");
        }
        if (orderItems == null) {
            logger.error("Order not provided");
            throw new InvalidInputException("Order not provided");
        }
        orderDAO.addOrder(customerID, orderItems);
        return Response.status(Response.Status.CREATED).entity("Order created").build();
    }
    
    static class OrderRequest {

        private Map<Integer, Integer> orderItems;
        public OrderRequest() {}

        public OrderRequest(Map<Integer, Integer> orderItems) {
            this.orderItems = orderItems;
        }

        public Map<Integer, Integer> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(Map<Integer, Integer> orderItems) {
            this.orderItems = orderItems;
        }
    }
    
}
