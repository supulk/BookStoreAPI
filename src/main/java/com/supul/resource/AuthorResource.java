/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.resource;

import com.supul.dao.AuthorDAO;
import com.supul.exception.InvalidInputException;
import com.supul.model.Author;
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
@Path("/authors")
public class AuthorResource {
    private AuthorDAO authorDAO = new AuthorDAO();
    private static final Logger logger = LoggerFactory.getLogger(AuthorResource.class);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAuthors(){
        return Response.status(Response.Status.OK)
                    .entity(authorDAO.getallAuthors())
                    .build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorById(@PathParam("id") int id){
        if (id <= 0) {
            logger.error("Invald author id provided");
            throw new InvalidInputException("Invalid author id provided");
        }
        return Response.status(Response.Status.OK)
                    .entity(authorDAO.getAuthorById(id))
                    .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAuthor(Author author){
        if (author == null) {
            logger.error("Empty author object provided");
            throw new InvalidInputException("Author object cannot be null");
        }
        authorDAO.addAuthor(author);
        return Response.status(Response.Status.CREATED).entity("Author : "+author.getName()+" created").build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") int id, Author author) throws Exception{
        if (id <= 0) {
            logger.error("Invald author id provided");
            throw new InvalidInputException("Invalid author id provided");
        }
        if (author == null) {
            logger.error("Empty author object provided");
            throw new InvalidInputException("Author object cannot be null");
        }
        authorDAO.updateAuthor(id, author);
        return Response.status(Response.Status.OK).entity("Author : "+author.getName()+" updated").build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id){
        if (id <= 0) {
            logger.error("Invald author id provided");
            throw new InvalidInputException("Invalid author id provided");
        }
        Author author = authorDAO.deleteAuthor(id);
        return Response.status(Response.Status.OK).entity("Author : "+author.getName()+" deleted").build();
    }
    
    @GET
    @Path("/{id}/books")
    public Response getBooksOfAuthor(@PathParam("id") int authorId){
        if (authorId <= 0) {
            logger.error("Invald author id provided");
            throw new InvalidInputException("Invalid author id provided");
        }
        return Response.status(Response.Status.OK)
                .entity(authorDAO.getBooksOfAuthor(authorId))
                .build();
    }
}
