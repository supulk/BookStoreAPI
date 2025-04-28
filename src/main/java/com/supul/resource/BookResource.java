/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.resource;

import com.supul.dao.BookDAO;
import com.supul.exception.InvalidInputException;
import com.supul.model.Book;
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
@Path("/books")
public class BookResource {
    private BookDAO bookDAO = new BookDAO();
    private static final Logger logger = LoggerFactory.getLogger(BookResource.class);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks(){
        return Response.status(Response.Status.OK)
                    .entity(bookDAO.getAllBooks())
                    .build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("id") int id){
        if (id <= 0) {
            logger.error("Invald book id provided");
            throw new InvalidInputException("Invald book id provided");
        }
        return Response.status(Response.Status.OK)
                    .entity(bookDAO.getBookById(id))
                    .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(Book book){
        if (book == null) {
            logger.error("Empty book object provided");
            throw new InvalidInputException("Book object cannot be null");
        }
        bookDAO.addBook(book);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateBook(@PathParam("id") int id, Book book) throws Exception{
        if (id <= 0) {
            logger.error("Invald book id provided");
            throw new InvalidInputException("Invald book id provided");
        }
        if (book == null){
            logger.error("Empty book object provided");
            throw new InvalidInputException("Book object cannot be null");
        }
        bookDAO.updateBook(id, book);
        return Response.status(Response.Status.OK).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id){
        if (id <= 0) {
            logger.error("Invald book id provided");
            throw new InvalidInputException("Invald book id provided");
        }
        bookDAO.deleteBook(id);
        return Response.status(Response.Status.OK).build();
    }
}
