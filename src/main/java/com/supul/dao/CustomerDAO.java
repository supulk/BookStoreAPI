/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supul.dao;

import com.supul.exception.CustomerNotFoundException;
import com.supul.exception.InvalidInputException;
import com.supul.model.Customer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Supul
 */
public class CustomerDAO {
    private static List<Customer> customers = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(CustomerDAO.class);
    
    static{
        customers.add(new Customer("John Smith", "john.smith@example.com", "Secure123!"));
        customers.get(0).setId(1);
        customers.add(new Customer("Emily Johnson", "emily.j@mailservice.com", "P@ssw0rd"));
        customers.get(1).setId(2);
        customers.add(new Customer("Michael Brown", "michael.b@business.org", "Brownie2023"));
        customers.get(2).setId(3);
        customers.add(new Customer("Sarah Williams", "sarah.w@university.edu", "Ac@d3m1c"));
        customers.get(3).setId(4);
        customers.add(new Customer("David Lee", "david.lee@tech.io", "T3chN0logy"));
        customers.get(4).setId(5);
        customers.add(new Customer("Jennifer Davis", "j.davis@example.net", "J3nn1f3r"));
        customers.get(5).setId(6);
        customers.add(new Customer("Robert Wilson", "rob.wilson@service.com", "W1ls0nR0b"));
        customers.get(6).setId(7);
        customers.add(new Customer("Lisa Martinez", "l.martinez@mail.org", "Mart1nez!"));
        customers.get(7).setId(8);
        customers.add(new Customer("Thomas Taylor", "taylor.t@company.com", "T@yl0r123"));
        customers.get(8).setId(9);
        customers.add(new Customer("Amanda Anderson", "a.anderson@webmail.com", "And3rs0n!"));
        customers.get(9).setId(10);
    }
    
    public List<Customer> getAllCustomers(){
        logger.info("Get all Customers request");
        return customers;
    }
    
    
    public Customer getCustomerById(int id){
        logger.info("Get customer by Id: " + id);
        return customers
                .stream()
                .filter(current -> current.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Customer with the id " + id + " not found"));
    }
    
    
    public void addCustomer(Customer customer){
        logger.info("Add customer : " + customer.getName());
        customer.setId(nextCustomerId());
        customers.add(customer);
    }
    
    
    public void updateCustomer(int pathId, Customer customer){
        // Use pathid if provided, otherwise use the id of the customer object
        int id = (pathId != 0) ? pathId : customer.getId();
        if (id == 0) {
            throw new InvalidInputException("Id must be provided");
        }

        Customer customerToUpdate = customers.stream()
                .filter(current -> current.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));

        //updating only the client provided entries
        if (customer.getName() != null) {
            customerToUpdate.setName(customer.getName());
            logger.info("Customer name changed: " + id);
        }
        if (customer.getEmail() != null) {
            customerToUpdate.setEmail(customer.getEmail());
            logger.info("Customer Email changed: " + id);
        }
        if (customer.getPassword() != null) {
            customerToUpdate.setPassword(customer.getPassword());
            logger.info("Customer password changed: " + id);
        }
    }
    
    
    public Customer deleteCustomer(int id){
        Customer customerToDelete = customers.stream()
                .filter(current -> current.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));
        
        customers.remove(customerToDelete);
        return customerToDelete;
    }
    
    public int nextCustomerId(){
        int currentMax = Integer.MIN_VALUE;
        for(Customer customer: customers){
            int id = customer.getId();
            if(id > currentMax){currentMax = id;}
        }
        return currentMax + 1;
    }
}