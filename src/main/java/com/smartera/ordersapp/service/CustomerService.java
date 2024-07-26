package com.smartera.ordersapp.service;

import com.smartera.ordersapp.model.Customer;
import com.smartera.ordersapp.model.Order;

import java.util.List;

public interface CustomerService {
    void save(Customer customer);
    Customer findById(int customerId);
    List<Customer> findAll();
    List<Customer> findByKeyword(String keyword);
    void update(Customer customer);
    void deleteById(int customerId);
    void deleteAll();
    void authorize(int customerId);
    void saveOrder(int customerId, Order orderId);
    void updateOrder(int customerId, Order order);
    void deleteOrder(int customerId, int orderId);
    void deleteOrder(int customerId);
    void checkAuthorization(int customerId);

}
