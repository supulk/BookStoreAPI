/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.dao;

import com.supul.exception.CustomerNotFoundException;
import com.supul.model.Cart;
import com.supul.model.Customer;
import com.supul.model.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Supul
 */
public class OrderDAO {
    private static Map<Integer, List<Order>> orders = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(OrderDAO.class);
    private static CustomerDAO customerDAO = new CustomerDAO();
    private static BookDAO bookDAO = new BookDAO();
    private static CartDAO cartDAO = new CartDAO();
    
    static{
        for (Customer customer : customerDAO.getAllCustomers()) {
            orders.put(customer.getId(), new ArrayList<>());
        }
        Cart cart1 = new Cart(customerDAO.getCustomerById(1));
        cart1.addItem(bookDAO.getBookById(3), 2);
        cart1.addItem(bookDAO.getBookById(4), 1);
        Order order1 = new Order(cart1, 5600);
        List<Order> value1 = new ArrayList<>();
        value1.add(order1);
        orders.put(1, value1);
        
    }
    
    public List<Order> getAllOrders(int customerId){
        logger.info("Get request for all orders");
        
        List<Order> currentCustomerOrders = orders.get(customerId);
        if (currentCustomerOrders == null) {
            logger.error("Customer does not have any orders");
            throw new CustomerNotFoundException("Customer does not have any orders");
        }
        return currentCustomerOrders;
    }
    
    public Order getOrderById(int customerId, int orderId){
        logger.info("Get request for order : " + orderId);
        List<Order> customerOrders =  getAllOrders(customerId);
        Order currentOrder = new Order();
        for(Order order: customerOrders){
            if(order.getId() == orderId){
                currentOrder = order;
            }
        }
        if (currentOrder == null) {
            logger.error("No order found for specified id");
            return new Order();
        }
        return currentOrder;
    }
    
    public void addOrder(int customerId, Order order){
        if(orders.containsKey(customerId)){
            List<Order> customerOrders = orders.get(customerId);
            int oderListSize = customerOrders.size();
            order.setId(oderListSize+1);
            customerOrders.add(order);
        }else{
            List<Order> newList = new ArrayList<>();
            order.setId(1);
            newList.add(order);
            orders.put(customerId, newList);
        }
    }
    
    
}
