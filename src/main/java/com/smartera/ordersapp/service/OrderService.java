package com.smartera.ordersapp.service;

import com.smartera.ordersapp.model.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);
    Order findById(int orderId);
    List<Order> findAll();
    List<Order> findByKeyword(String keyword);
    void update(Order order);
    void deleteById(int orderId);
    void deleteAll();
    List<Order> findByCustomerId(int customerId);
    List<Order> findByCustomerIdKeyword(int customerId, String keyword);
    void deleteByCustomerId(int customerId);
}
