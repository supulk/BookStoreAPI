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
public class Order {
    private int id;
    private int customerId;
    private int total;
    private Map<Integer, Integer> orderItems = new HashMap<>();
    
    public Order(){}

    public Order(int customerId, int total, Map<Integer, Integer> orderItems) {
        this.orderItems = orderItems;
        this.customerId = customerId;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<Integer, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<Integer, Integer> orderItems) {
        this.orderItems = orderItems;
    }
    
    
    @Override
    public String toString(){
        return "Items: "+
                "\n"+orderItems.toString()+
                "\n"+"Total: "+total;
    }
}
