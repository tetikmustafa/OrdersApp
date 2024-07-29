package com.smartera.ordersapp.service;

import com.smartera.ordersapp.entity.Customer;
import com.smartera.ordersapp.entity.Order;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    void save(Customer customer);
    Customer findById(UUID customerId);
    List<Customer> findAll();
    List<Customer> findByKeyword(String keyword);
    void update(Customer customer);
    void deleteById(UUID customerId);
    void deleteAll();
    void authorize(UUID customerId);
    void saveOrder(UUID customerId, Order orderId);
    void updateOrder(UUID customerId, Order order);
    void deleteOrder(UUID customerId, UUID orderId);
    void deleteOrder(UUID customerId);
    void checkAuthorization(UUID customerId);

}
