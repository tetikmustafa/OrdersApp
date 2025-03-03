package com.smartera.ordersapp.service;

import com.smartera.ordersapp.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    void save(Order order);
    void save(Order order, UUID customerId);
    Order findById(UUID orderId);
    List<Order> findAll();
    List<Order> findByKeyword(String keyword);
    void update(Order order);
    void deleteById(UUID orderId);
    void deleteAll();
    List<Order> findByCustomerId(UUID customerId);
    List<Order> findByCustomerIdKeyword(UUID customerId, String keyword);
    void deleteByCustomerId(UUID customerId);
}
