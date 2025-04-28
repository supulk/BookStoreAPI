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
        // Order 1 - Customer 1 (John Smith)
        Cart cart1 = new Cart(customerDAO.getCustomerById(1));
        cart1.addItem(bookDAO.getBookById(3), 2);  // Malibu Rising, 2 copies
        cart1.addItem(bookDAO.getBookById(4), 1);  // The Last Thing He Told Me, 1 copy
        Order order1 = new Order(cart1, 3000);
        order1.setId(1);
        List<Order> value1 = new ArrayList<>();
        value1.add(order1);
        orders.put(1, value1);

        // Order 2 - Customer 2 (Emily Johnson)
        Cart cart2 = new Cart(customerDAO.getCustomerById(2));
        cart2.addItem(bookDAO.getBookById(10), 3); // Project Hail Mary, 3 copies
        Order order2 = new Order(cart2, 5000);
        order2.setId(1);
        List<Order> value2 = new ArrayList<>();
        value2.add(order2);
        orders.put(2, value2);

        // Order 3 - Customer 3 (Michael Brown)
        Cart cart3 = new Cart(customerDAO.getCustomerById(3));
        cart3.addItem(bookDAO.getBookById(1), 1);  // The Silent Patient
        cart3.addItem(bookDAO.getBookById(6), 1);  // Where the Crawdads Sing
        Order order3 = new Order(cart3, 4500);
        order3.setId(1);
        List<Order> value3 = new ArrayList<>();
        value3.add(order3);
        orders.put(3, value3);

        // Order 4 - Customer 4 (Sarah Williams)
        Cart cart4 = new Cart(customerDAO.getCustomerById(4));
        cart4.addItem(bookDAO.getBookById(5), 2);  // Crying in H Mart
        cart4.addItem(bookDAO.getBookById(8), 1);  // The Midnight Library
        Order order4 = new Order(cart4, 6700);
        order4.setId(1);
        List<Order> value4 = new ArrayList<>();
        value4.add(order4);
        orders.put(4, value4);

        // Order 5 - Customer 5 (David Lee)
        Cart cart5 = new Cart(customerDAO.getCustomerById(5));
        cart5.addItem(bookDAO.getBookById(2), 1);  // The Four Winds
        cart5.addItem(bookDAO.getBookById(9), 1);  // Atomic Habits
        Order order5 = new Order(cart5, 7000);
        order5.setId(1);
        List<Order> value5 = new ArrayList<>();
        value5.add(order5);
        orders.put(5, value5);

        // Order 6 - Customer 6 (Jennifer Davis)
        Cart cart6 = new Cart(customerDAO.getCustomerById(6));
        cart6.addItem(bookDAO.getBookById(7), 1);  // Educated
        Order order6 = new Order(cart6, 1000);
        order6.setId(1);
        List<Order> value6 = new ArrayList<>();
        value6.add(order6);
        orders.put(6, value6);

        // Order 7 - Customer 7 (Robert Wilson)
        Cart cart7 = new Cart(customerDAO.getCustomerById(7));
        cart7.addItem(bookDAO.getBookById(3), 1);  // Malibu Rising
        cart7.addItem(bookDAO.getBookById(6), 1);  // Where the Crawdads Sing
        Order order7 = new Order(cart7, 2500);
        order7.setId(1);
        List<Order> value7 = new ArrayList<>();
        value7.add(order7);
        orders.put(7, value7);

        // Order 8 - Customer 8 (Lisa Martinez)
        Cart cart8 = new Cart(customerDAO.getCustomerById(8));
        cart8.addItem(bookDAO.getBookById(4), 2);  // The Last Thing He Told Me
        Order order8 = new Order(cart8, 850);
        order8.setId(1);
        List<Order> value8 = new ArrayList<>();
        value8.add(order8);
        orders.put(8, value8);

        // Order 9 - Customer 9 (Thomas Taylor)
        Cart cart9 = new Cart(customerDAO.getCustomerById(9));
        cart9.addItem(bookDAO.getBookById(10), 1); // Project Hail Mary
        cart9.addItem(bookDAO.getBookById(1), 1);  // The Silent Patient
        Order order9 = new Order(cart9, 2300);
        order9.setId(1);
        List<Order> value9 = new ArrayList<>();
        value9.add(order9);
        orders.put(9, value9);

        // Order 10 - Customer 10 (Amanda Anderson)
        Cart cart10 = new Cart(customerDAO.getCustomerById(10));
        cart10.addItem(bookDAO.getBookById(8), 1);  // The Midnight Library
        cart10.addItem(bookDAO.getBookById(5), 1);  // Crying in H Mart
        Order order10 = new Order(cart10, 1500);
        order10.setId(1);
        List<Order> value10 = new ArrayList<>();
        value10.add(order10);
        orders.put(10, value10);
        
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
