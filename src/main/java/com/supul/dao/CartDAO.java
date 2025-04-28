/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.dao;

import com.supul.exception.BookNotFoundException;
import com.supul.exception.CartNotFoundException;
import com.supul.exception.OutOfStockException;
import com.supul.model.Book;
import com.supul.model.Cart;
import com.supul.model.Customer;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Supul
 */
public class CartDAO {
    private static Map<Integer, Cart> carts = new HashMap<>();
    private static CustomerDAO customerDAO = new CustomerDAO();
    private static BookDAO bookDAO = new BookDAO();
    private static final Logger logger = LoggerFactory.getLogger(CartDAO.class);
    
    static{
        // Cart 1: John Smith (ID 1) - 2 books
        Cart cart1 = new Cart(customerDAO.getCustomerById(1));
        cart1.addItem(bookDAO.getBookById(1), 1);  // The Silent Patient (ID 1), qty 1
        cart1.addItem(bookDAO.getBookById(6), 2);  // Where the Crawdads Sing (ID 6), qty 2
        carts.put(1, cart1);

        // Cart 2: Emily Johnson (ID 2) - 1 book with high quantity
        Cart cart2 = new Cart(customerDAO.getCustomerById(2));
        cart2.addItem(bookDAO.getBookById(10), 3);  // Project Hail Mary (ID 10), qty 3
        carts.put(2, cart2);

        // Cart 3: Michael Brown (ID 3) - Empty cart
        Cart cart3 = new Cart(customerDAO.getCustomerById(3));
        carts.put(3, cart3);

        // Cart 4: Sarah Williams (ID 4) - 3 different books
        Cart cart4 = new Cart(customerDAO.getCustomerById(4));
        cart4.addItem(bookDAO.getBookById(3), 1);  // Malibu Rising (ID 3), qty 1
        cart4.addItem(bookDAO.getBookById(5), 1);  // Crying in H Mart (ID 5), qty 1
        cart4.addItem(bookDAO.getBookById(8), 2);  // The Midnight Library (ID 8), qty 2
        carts.put(4, cart4);

        // Cart 5: David Lee (ID 5) - Popular books
        Cart cart5 = new Cart(customerDAO.getCustomerById(5));
        cart5.addItem(bookDAO.getBookById(9), 1);  // Atomic Habits (ID 9), qty 1
        cart5.addItem(bookDAO.getBookById(2), 1);  // The Four Winds (ID 2), qty 1
        carts.put(5, cart5);
    }
    
    public Map<String, Integer> getCart(int customerId){
        Cart cart = carts.get(customerId);
        if(cart == null){
            logger.error("Requested cart not found");
            throw new CartNotFoundException("Customer "+ customerDAO.getCustomerById(customerId).getName() + "does not have a cart");
        }else{
            logger.info("Fetching cart for : "+customerDAO.getCustomerById(customerId).getName());
            return cart.getCartItemsPretty();}
    }
    
    
    
    public void addToCart(int customerId, int bookId){
        Cart cart = carts.computeIfAbsent(customerId, key -> new Cart(customerDAO.getCustomerById(key)));
        Book book = bookDAO.getBookById(bookId);
        if (book.getStock() <= 0) {
            throw new OutOfStockException("Requested book is out of stock");
        }
        cart.addItem(book, 1);
        logger.info("Added" +book.getTitle() +"to cart of : " + customerDAO.getCustomerById(customerId).getName());
    }
    
    
    public void updateCart(int customerId, int bookId, int quantity){
        Cart cart = carts.computeIfAbsent(customerId, key -> new Cart(customerDAO.getCustomerById(key)));
        Book book = bookDAO.getBookById(bookId);
        if (book.getStock() < quantity) {
            throw new OutOfStockException("Requested book is not available in given quantity,. Available : "+ book.getStock());
        }
        
        Map<Book, Integer> booksInTheCart = cart.getCartItems();
        if(!booksInTheCart.containsKey(book)){
            logger.error("Requested book is not in the cart, adding it...");
        }
        
        cart.addItem(book, quantity);
        logger.info("Cart of : " + customerDAO.getCustomerById(customerId).getName() + ", Book :" + book.getTitle() + ", quantity updated to :" + quantity);
    }
    
    
    public void deleteFromCart(int customerId, int bookId){
        Cart cart = carts.get(customerId);
        Book book = bookDAO.getBookById(bookId);
        if (cart == null){
            logger.error("Requested cart not found");
            throw new CartNotFoundException("Customer " + customerDAO.getCustomerById(customerId).getName() + "does not have a cart");
        }
        
        Map<Book, Integer> booksInTheCart = cart.getCartItems();
        if(!booksInTheCart.containsKey(book)){
            logger.error("Requested book is not in the cart");
            throw new BookNotFoundException("Requested book is not in the cart");
        }
        
        cart.removeBookFromCart(book);
        logger.info("Book removed");
    }
}
