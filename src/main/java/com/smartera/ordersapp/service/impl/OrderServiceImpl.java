package com.smartera.ordersapp.service.impl;

import com.smartera.ordersapp.exception.OrderNotFoundException;
import com.smartera.ordersapp.model.Order;
import com.smartera.ordersapp.repository.OrderRepository;
import com.smartera.ordersapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    public void save(Order order) {
        save(order, order.getOrderCustomerId());

    }

    public Order findById(@PathVariable int orderId) {
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
        int customerId = o.get().getOrderCustomerId();
        order.setOrderCustomerId(customerId);
        orderRepository.save(order);
        customerServiceImpl.updateOrder(customerId, order);

    }

    public void deleteById(@PathVariable int orderId) {
        Optional<Order> o = orderRepository.findById(orderId);
        if (o.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }
        int customerId = o.get().getOrderCustomerId();
        customerServiceImpl.deleteOrder(customerId, orderId);
        orderRepository.deleteById(orderId);
    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }

    public void save(Order order, int customerId) {
        customerServiceImpl.checkAuthorization(customerId);
        order.setOrderCustomerId(customerId);
        orderRepository.save(order);
        customerServiceImpl.saveOrder(customerId, order);
    }

    public List<Order> findByCustomerId(int customerId) {
        return orderRepository.findByOrderCustomerId(customerId);
    }


    public List<Order> findByCustomerIdKeyword(int customerId, String keyword) {
        return orderRepository.findByOrderCustomerIdAndOrderDescriptionContaining(customerId, keyword);
    }

    public void deleteByCustomerId(int customerId) {
        List<Order> orders = orderRepository.findByOrderCustomerId(customerId);
        for (Order order : orders) {
            orderRepository.deleteById(order.getOrderId());
        }
        customerServiceImpl.deleteOrder(customerId);
    }
}
