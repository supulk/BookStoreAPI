/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.dao;

import com.supul.exception.BookNotFoundException;
import com.supul.exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.supul.model.Book;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Supul
 */
public class BookDAO {
    private static List<Book> books = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(BookDAO.class);
    
    static{
        books.add(new Book("The Silent Patient", 1, 1250316778, 2019, 12.99, 15));
        books.get(0).setId(1);
        books.add(new Book("The Four Winds", 2, 1250178638, 2021, 15.50, 14));
        books.get(1).setId(2);
        books.add(new Book("Malibu Rising", 3, 1524798676, 2021, 13.25, 16));
        books.get(2).setId(3);
        books.add(new Book("The Last Thing He Told Me", 4, 1501171378, 2021, 12.75, 12));
        books.get(3).setId(4);
        books.add(new Book("Crying in H Mart", 4, 1984898953, 2021, 17.00, 8));
        books.get(4).setId(5);
        books.add(new Book("Where the Crawdads Sing", 4, 1984745953, 2018, 14.99, 20));
        books.get(5).setId(6);
        books.add(new Book("Educated", 5, 1989590518, 2018, 13.50, 12));
        books.get(6).setId(7);
        books.add(new Book("The Midnight Library", 6, 715559483, 2020, 16.00, 18));
        books.get(7).setId(8);
        books.add(new Book("Atomic Habits", 7, 1505211309, 2018, 11.99, 25));
        books.get(8).setId(9);
        books.add(new Book("Project Hail Mary", 8, 0503135222, 2021, 14.99, 0));
        books.get(9).setId(10);
    }
    
    public List<Book> getAllBooks(){
        logger.info("Get All Books request");
        return books;
    }
    
    public Book getBookById(int id){
        logger.info("Get book by Id: " + id);
        return books
                .stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book with the id " + id + "not found"));
        }
    
    public void addBook(Book book){
        logger.info("Add book : " + book.getTitle());
        if (book.getId() <= 0 ){
            book.setId(nextBookId());
        }
        book.setId(nextBookId());
        books.add(book);
    }
    
    
    public void updateBook(int pathId, Book book){
        // Use pathid if provided, otherwise use the id of the book object
        int id = (pathId != 0) ? pathId : book.getId();
        if (id == 0) {
            throw new InvalidInputException("ID must be provided");
        }

        Book bookToUpdate = books.stream()
                .filter(currentBook -> currentBook.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));

        
        //updating only the client provided entries
        if (book.getTitle() != null) {
            bookToUpdate.setTitle(book.getTitle());
        }
        if (book.getAuthorId() > 0) {
            bookToUpdate.setAuthorId(book.getAuthorId());
        }
        if (book.getISBN() != 0) {
            bookToUpdate.setISBN(book.getISBN());
        }
        if (book.getYear() != 0) {
            bookToUpdate.setYear(book.getYear());
        }
        if (book.getPrice() != 0.0) {
            bookToUpdate.setPrice(book.getPrice());
        }
        if (book.getStock() != 0) {
            bookToUpdate.setStock(book.getStock());
        }
    }
    
    
    public void deleteBook(int id){
        Book bookToDelete = books.stream()
                .filter(currentBook -> currentBook.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
        
        books.remove(bookToDelete);
    }
    
    public int nextBookId(){
        int currentMax = Integer.MIN_VALUE;
        for(Book book: books){
            int id = book.getId();
            if(id > currentMax){currentMax = id;}
        }
        return currentMax + 1;
    }
}
