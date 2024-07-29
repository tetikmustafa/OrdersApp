package com.smartera.ordersapp.service.impl;

import com.smartera.ordersapp.exception.OrderNotFoundException;
import com.smartera.ordersapp.entity.Order;
import com.smartera.ordersapp.repository.OrderRepository;
import com.smartera.ordersapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private CustomerServiceImpl customerService;

    public void save(Order order) {
        save(order, order.getOrderCustomerId());

    }

    public Order findById(@PathVariable UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByKeyword(String keyword) {
        return orderRepository.findByOrderNameContainingOrOrderDescriptionContaining(keyword,keyword);
    }

    public void update(Order order) {
        Optional<Order> o = orderRepository.findById(order.getOrderId());
        if (o.isEmpty()) {
            throw new OrderNotFoundException(order.getOrderId());
        }
        UUID customerId = o.get().getOrderCustomerId();
        order.setOrderCustomerId(customerId);
        orderRepository.save(order);
        customerService.updateOrder(customerId, order);

    }

    public void deleteById(@PathVariable UUID orderId) {
        Optional<Order> o = orderRepository.findById(orderId);
        if (o.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }
        UUID customerId = o.get().getOrderCustomerId();
        customerService.deleteOrder(customerId, orderId);
        orderRepository.deleteById(orderId);
    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }

    public void save(Order order, UUID customerId) {
        customerService.checkAuthorization(customerId);
        order.setOrderCustomerId(customerId);
        orderRepository.save(order);
        customerService.saveOrder(customerId, order);
    }

    public List<Order> findByCustomerId(UUID customerId) {
        return orderRepository.findByOrderCustomerId(customerId);
    }


    public List<Order> findByCustomerIdKeyword(UUID customerId, String keyword) {
        return orderRepository.findByOrderCustomerIdAndOrderDescriptionContaining(customerId, keyword);
    }

    public void deleteByCustomerId(UUID customerId) {
        List<Order> orders = orderRepository.findByOrderCustomerId(customerId);
        for (Order order : orders) {
            orderRepository.deleteById(order.getOrderId());
        }
        customerService.deleteOrder(customerId);
    }
}
