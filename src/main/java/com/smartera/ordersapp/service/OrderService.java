package com.smartera.ordersapp.service;

import com.smartera.ordersapp.exception.OrderNotFoundException;
import com.smartera.ordersapp.model.Order;
import com.smartera.ordersapp.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IService<Order> {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    private CustomerService customerService;

    public void save(Order order) {
        save(order, order.getOrderCustomerId());

    }

    public Order findById(@PathVariable int orderId) {
        return orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public List<Order> findByKeyword(String keyword) {
        return orderRepo.findByOrderNameContainingOrOrderDescriptionContaining(keyword,keyword);
    }

    public void update(Order order) {
        Optional<Order> o = orderRepo.findById(order.getOrderId());
        if (o.isEmpty()) {
            throw new OrderNotFoundException(order.getOrderId());
        }
        int customerId = o.get().getOrderCustomerId();
        order.setOrderCustomerId(customerId);
        orderRepo.save(order);
        customerService.updateOrder(customerId, order);

    }

    public void deleteById(@PathVariable int orderId) {
        Optional<Order> o = orderRepo.findById(orderId);
        if (o.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }
        int customerId = o.get().getOrderCustomerId();
        customerService.deleteOrder(customerId, orderId);
        orderRepo.deleteById(orderId);
    }

    public void deleteAll() {
        orderRepo.deleteAll();
    }

    public void save(Order order, int customerId) {
        customerService.checkAuthorization(customerId);
        order.setOrderCustomerId(customerId);
        orderRepo.save(order);
        customerService.saveOrder(customerId, order);
    }

    public List<Order> findByCustomerId(int customerId) {
        return orderRepo.findByOrderCustomerId(customerId);
    }


    public List<Order> findByCustomerIdKeyword(int customerId, String keyword) {
        return orderRepo.findByOrderCustomerIdAndOrderDescriptionContaining(customerId, keyword);
    }

    public void deleteByCustomerId(int customerId) {
        List<Order> orders = orderRepo.findByOrderCustomerId(customerId);
        for (Order order : orders) {
            orderRepo.deleteById(order.getOrderId());
        }
        customerService.deleteOrder(customerId);
    }
}
