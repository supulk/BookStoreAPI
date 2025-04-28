/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.resource;

import com.supul.dao.CustomerDAO;
import com.supul.exception.InvalidInputException;
import com.supul.model.Author;
import com.supul.model.Customer;
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
@Path("/customers")
public class CustomerResource {
    private CustomerDAO customerDAO = new CustomerDAO();
    private static final Logger logger = LoggerFactory.getLogger(CustomerResource.class);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers(){
        return Response.status(Response.Status.OK)
                    .entity(customerDAO.getAllCustomers())
                    .build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") int id){
        if (id <= 0) {
            logger.error("Invald customer id provided");
            throw new InvalidInputException("Invalid customer id provided");
        }
        return Response.status(Response.Status.OK)
                    .entity(customerDAO.getCustomerById(id))
                    .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer){
        if (customer == null) {
            logger.error("empty customer id provided");
            throw new InvalidInputException("empty customer id provided");
        }
        customerDAO.addCustomer(customer);
        return Response.status(Response.Status.CREATED).entity("Customer : "+customer.getName()+" created").build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") int id, Customer customer){
        if (id <= 0) {
            logger.error("invalid customer id provided");
            throw new InvalidInputException("invalid customer id provided");
        }
        if (customer == null) {
            logger.error("empty customer id provided");
            throw new InvalidInputException("empty customer id provided");
        }
        customerDAO.updateCustomer(id, customer);
        return Response.status(Response.Status.OK).entity("Customer : "+customer.getName()+" updated").build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id){
        if (id <= 0) {
            logger.error("invalid customer id provided");
            throw new InvalidInputException("invalid customer id provided");
        }
        Customer customer = customerDAO.deleteCustomer(id);
        return Response.status(Response.Status.OK).entity("Customer : "+customer.getName()+" deleted").build();
    }
}
