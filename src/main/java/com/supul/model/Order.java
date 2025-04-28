/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.model;

/**
 *
 * @author Supul
 */
public class Order {
    private int id;
    private Cart cart;
    private int price;
    private Customer customer;
    
    public Order(){}

    public Order(Cart cart, int price) {
        this.cart = cart;
        this.price = price;
        this.customer = cart.getCustomer();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Customer getCustomer() {
        if(customer == null && cart!= null){
            customer=cart.getCustomer();
        }
        return customer;
    }
}
