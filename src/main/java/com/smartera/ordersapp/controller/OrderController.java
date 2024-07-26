package com.smartera.ordersapp.controller;

import com.smartera.ordersapp.model.Order;
import com.smartera.ordersapp.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController{

    @Autowired
    OrderServiceImpl orderServiceImpl;

    @PostMapping("orders")
    public Order save(@RequestBody Order order) {
        orderServiceImpl.save(order);
        return orderServiceImpl.findById(order.getOrderId());
    }

    @PostMapping({"orders/byCustomerId/{customerId}", "customers/{customerId}/orders"})
    public Order save(@RequestBody Order order, @PathVariable int customerId) {
        orderServiceImpl.save(order, customerId);
        return orderServiceImpl.findById(order.getOrderId());
    }

    @GetMapping("orders/{orderId}")
    public Order findById(@PathVariable int orderId) {
        return orderServiceImpl.findById(orderId);
    }

    @GetMapping("orders")
    public List<Order> findAll() {
        return orderServiceImpl.findAll();
    }

    @GetMapping("orders/keyword/{keyword}")
    public List<Order> findByKeyword(@PathVariable String keyword) {
        return orderServiceImpl.findByKeyword(keyword);
    }

    @GetMapping({"orders/byCustomerId/{customerId}", "/customers/{customerId}/orders"})
    public List<Order> findByCustomerId(@PathVariable int customerId) {
        return orderServiceImpl.findByCustomerId(customerId);
    }

    @GetMapping({"orders/byCustomerId/{customerId}/{keyword}", "/customers/{customerId}/orders/{keyword}"})
    public List<Order> findByKeyword(@PathVariable int customerId, @PathVariable String keyword) {
        return orderServiceImpl.findByCustomerIdKeyword(customerId, keyword);
    }

    @PutMapping("orders/{orderId}")
    public Order update(@RequestBody Order order, @PathVariable int orderId) {
        order.setOrderId(orderId);
        orderServiceImpl.update(order);
        return orderServiceImpl.findById(order.getOrderId());
    }


    @DeleteMapping("orders/{orderId}")
    public String deleteById(@PathVariable int orderId) {
        orderServiceImpl.deleteById(orderId);
        return "Order with id " + orderId + " has been deleted";
    }


    @DeleteMapping({"orders/byCustomerId/{customerId}", "customers/{customerId}/orders"})
    public String deleteByCustomerId(@PathVariable int customerId) {
        orderServiceImpl.deleteByCustomerId(customerId);
        return "Orders of customer with id " + customerId + " have been deleted";
    }

    @DeleteMapping("orders")
    public String deleteAll() {
        orderServiceImpl.deleteAll();
        return "All orders have been deleted";
    }
}
