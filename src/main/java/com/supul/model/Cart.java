/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.model;
 

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Supul
 */
public class Cart {
    private Customer customer;
    private Map<Book, Integer> cartItems = new HashMap<>();
    
    public Cart(){}

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Book, Integer> getCartItems() {
        return cartItems;
    }
    
    public Map<String, Integer> getCartItemsPretty() {
        Map<String, Integer> returnCart = new HashMap<>();
        for (Map.Entry<Book, Integer> entry : cartItems.entrySet()) {
            Book book = entry.getKey();
            Integer value = entry.getValue();
            
            returnCart.put(book.getTitle(), value);
        }
        return returnCart;
    }

    public void removeBookFromCart(Book book){
        if(cartItems.containsKey(book)){
            cartItems.remove(book);
        }
    }
    
    public void addItem(Book book, int qty) {
        cartItems.put(book, qty);
    }
}
