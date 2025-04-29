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
        Map<Integer, Integer> order1Items = new HashMap<>();
        order1Items.put(3, 2);// Malibu Rising, 2 copies
        order1Items.put(4, 1);// The Last Thing He Told Me, 1 copy
        Order order1 = new Order(1, order1Items); 
        order1.setId(1);
        List<Order> orderListCustomer1 = new ArrayList<>();
        orderListCustomer1.add(order1);
        orders.put(1, orderListCustomer1);

        // Order 2 - Customer 2 (Emily Johnson)
        Map<Integer, Integer> order2Items = new HashMap<>();
        order2Items.put(10, 3); // Project Hail Mary, 3 copies
        Order order2 = new Order(2, order2Items);
        order2.setId(1);
        List<Order> orderListCustomer2 = new ArrayList<>();
        orderListCustomer2.add(order2);
        orders.put(2, orderListCustomer2);

        // Order 3 - Customer 3 (Michael Brown)
        Map<Integer, Integer> order3Items = new HashMap<>();
        order3Items.put(1, 1); // The Silent Patient, 1 copy
        order3Items.put(6, 1); // Where the Crawdads Sing, 1 copy
        Order order3 = new Order(3, order3Items);
        order3.setId(1);
        List<Order> orderListCustomer3 = new ArrayList<>();
        orderListCustomer3.add(order3);
        orders.put(3, orderListCustomer3);

        // Order 4 - Customer 4 (Sarah Williams)
        Map<Integer, Integer> order4Items = new HashMap<>();
        order4Items.put(5, 2); // Crying in H Mart, 2 copies
        order4Items.put(8, 1); // The Midnight Library, 1 copy
        Order order4 = new Order(4, order4Items);
        order4.setId(1);
        List<Order> orderListCustomer4 = new ArrayList<>();
        orderListCustomer4.add(order4);
        orders.put(4, orderListCustomer4);

        // Order 5 - Customer 5 (David Lee)
        Map<Integer, Integer> order5Items = new HashMap<>();
        order5Items.put(2, 1); // The Four Winds, 1 copy
        order5Items.put(9, 1); // Atomic Habits, 1 copy
        Order order5 = new Order(5, order5Items);
        order5.setId(1);
        List<Order> orderListCustomer5 = new ArrayList<>();
        orderListCustomer5.add(order5);
        orders.put(5, orderListCustomer5);

        // Order 6 - Customer 6 (Jennifer Davis)
        Map<Integer, Integer> order6Items = new HashMap<>();
        Order order6 = new Order(6, order6Items);
        order6.setId(1);
        List<Order> orderListCustomer6 = new ArrayList<>();
        orderListCustomer6.add(order6);
        orders.put(6, orderListCustomer6);

        // Order 7 - Customer 7 (Robert Wilson)
        Map<Integer, Integer> order7Items = new HashMap<>();
        order7Items.put(3, 1); // Malibu Rising, 1 copy
        order7Items.put(6, 1); // Where the Crawdads Sing, 1 copy
        Order order7 = new Order(7, order7Items);
        order7.setId(1);
        List<Order> orderListCustomer7 = new ArrayList<>();
        orderListCustomer7.add(order7);
        orders.put(7, orderListCustomer7);

        // Order 8 - Customer 8 (Lisa Martinez)
        Map<Integer, Integer> order8Items = new HashMap<>();
        order8Items.put(4, 2); // The Last Thing He Told Me, 2 copies
        Order order8 = new Order(8, order8Items);
        order8.setId(1);
        List<Order> orderListCustomer8 = new ArrayList<>();
        orderListCustomer8.add(order8);
        orders.put(8, orderListCustomer8);

        // Order 9 - Customer 9 (Thomas Taylor)
        Map<Integer, Integer> order9Items = new HashMap<>();
        order9Items.put(10, 1); // Project Hail Mary, 1 copy
        order9Items.put(1, 1); // The Silent Patient, 1 copy
        Order order9 = new Order(9, order9Items);
        order9.setId(1);
        List<Order> orderListCustomer9 = new ArrayList<>();
        orderListCustomer9.add(order9);
        orders.put(9, orderListCustomer9);

        // Order 10 - Customer 10 (Amanda Anderson)
        Map<Integer, Integer> order10Items = new HashMap<>();
        order10Items.put(8, 1); // The Midnight Library, 1 copy
        order10Items.put(5, 1); // Crying in H Mart, 1 copy
        Order order10 = new Order(10, order10Items);
        order10.setId(1);
        List<Order> orderListCustomer10 = new ArrayList<>();
        orderListCustomer10.add(order10);
        orders.put(10, orderListCustomer10);
        
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
        if (currentOrder.getId() <= 0) {
            logger.error("No order found for specified id");
            return new Order();
        }
        return currentOrder;
    }
    
    public void addOrder(int customerId, Map<Integer, Integer> orderItems){
        Customer customer = customerDAO.getCustomerById(customerId);
        if(orders.containsKey(customerId)){
            List<Order> customerOrders = orders.get(customerId);
            int nextId = customerOrders.size()+1;
            Order order = new Order(customerId, orderItems);
            order.setId(nextId);
            customerOrders.add(order);
        }else{
            List<Order> newList = new ArrayList<>();
            Order order = new Order(customerId, orderItems);
            order.setId(1);
            newList.add(order);
            orders.put(customerId, newList);
        }
    }
    
    
}
