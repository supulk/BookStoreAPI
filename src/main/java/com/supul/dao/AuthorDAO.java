/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.dao;

import com.supul.exception.AuthorNotFoundException;
import com.supul.exception.BookNotFoundException;
import com.supul.exception.InvalidInputException;
import com.supul.model.Author;
import com.supul.model.Book;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Supul
 */
public class AuthorDAO {
    private static List<Author> authors = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(AuthorDAO.class);
    private BookDAO bookDAO = new BookDAO();
    
    static{
        authors.add(new Author("J.K. Rowling",
                "British author best known for the Harry Potter fantasy series"));
        authors.get(0).setId(1);

        authors.add(new Author("George R.R. Martin",
                "American novelist and short-story writer, author of A Song of Ice and Fire"));
        authors.get(1).setId(2);

        authors.add(new Author("Stephen King",
                "American author of horror, supernatural fiction, suspense, and fantasy novels"));
        authors.get(2).setId(3);

        authors.add(new Author("Agatha Christie",
                "English writer known for her detective novels and short story collections"));
        authors.get(3).setId(4);

        authors.add(new Author("Haruki Murakami",
                "Japanese writer known for surrealistic fiction blending realism and fantasy"));
        authors.get(4).setId(5);

        authors.add(new Author("Margaret Atwood",
                "Canadian poet, novelist, and environmental activist, author of The Handmaid's Tale"));
        authors.get(5).setId(6);

        authors.add(new Author("Ernest Hemingway",
                "American novelist, short-story writer, and journalist known for his economical style"));
        authors.get(6).setId(7);

        authors.add(new Author("Toni Morrison",
                "American novelist and Nobel Prize winner known for works exploring Black identity"));
        authors.get(7).setId(8);

        authors.add(new Author("Gabriel García Márquez",
                "Colombian novelist and Nobel Prize winner, pioneer of magical realism"));
        authors.get(8).setId(9);

        authors.add(new Author("Jane Austen",
                "English novelist known for romantic fiction set among the landed gentry"));
        authors.get(9).setId(10);
    }
    
    public List<Author> getallAuthors(){
        logger.info("Get all Authors request");
        return authors;
    }
    
    
    public Author getAuthorById(int id){
        logger.info("Get author by Id: " + id);
        return authors
                .stream()
                .filter(author -> author.getId() == id)
                .findFirst()
                .orElseThrow(() -> new AuthorNotFoundException("Author with the id " + id + "not found"));
        }
    
    
    public boolean addAuthor(Author author){
        logger.info("Add Author : " + author.getName());
        author.setId(nextAuthorId());
        authors.add(author);
        return true;
    }
    
    
    public void updateAuthor(int pathId, Author author){
        // Use pathid if provided, otherwise use the id of the author object
        int id = (pathId != 0) ? pathId : author.getId();
        if (id == 0) {
            throw new InvalidInputException("Id must be provided");
        }

        Author authorToUpdate = authors.stream()
                .filter(currentAuthor -> currentAuthor.getId() == id)
                .findFirst()
                .orElseThrow(() -> new AuthorNotFoundException("Author with ID " + id + " not found"));

        
        //updating only the client provided entries
        if (author.getName()!= null) {
            authorToUpdate.setName(author.getName());
            logger.info("Author name changed: " + id);
        }
        if (author.getBiography() != null) {
            authorToUpdate.setBiography(author.getBiography());
            logger.info("Author Biography changed: " + id);
        }
    }
    
    
    public Author deleteAuthor(int id){
        Author authorToDelete = authors.stream()
                .filter(current -> current.getId() == id)
                .findFirst()
                .orElseThrow(() -> new AuthorNotFoundException("Author with ID " + id + " not found"));
        
        authors.remove(authorToDelete);
        return authorToDelete;
    }
    
    public List<Book> getBooksOfAuthor(int id){
        Author author = authors.stream()
                .filter(current -> current.getId() == id)
                .findFirst()
                .orElseThrow(() -> new AuthorNotFoundException("Author with ID " + id + " not found"));
        
        List<Book> authorBookList = new ArrayList<>();
        for(Book book : bookDAO.getAllBooks()){
            if (book.getAuthorId() == id){
                authorBookList.add(book);
            }
        }
        return authorBookList;
    }
    
    
    public int nextAuthorId(){
        int currentMax = Integer.MIN_VALUE;
        for(Author author: authors){
            int id = author.getId();
            if(id > currentMax){currentMax = id;}
        }
        return currentMax + 1;
    }
}
