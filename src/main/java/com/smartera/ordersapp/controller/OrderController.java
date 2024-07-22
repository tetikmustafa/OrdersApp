package com.smartera.ordersapp.controller;

import com.smartera.ordersapp.model.Order;
import com.smartera.ordersapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController implements IController<Order>{

    @Autowired
    OrderService orderService;

    @PostMapping("orders")
    public Order save(@RequestBody Order order) {
        orderService.save(order);
        return orderService.findById(order.getOrderId());
    }

    @PostMapping({"orders/byCustomerId/{customerId}", "customers/{customerId}/orders"})
    public Order save(@RequestBody Order order, @PathVariable int customerId) {
        orderService.save(order, customerId);
        return orderService.findById(order.getOrderId());
    }

    @GetMapping("orders/{orderId}")
    public Order findById(@PathVariable int orderId) {
        return orderService.findById(orderId);
    }

    @GetMapping("orders")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("orders/keyword/{keyword}")
    public List<Order> findByKeyword(@PathVariable String keyword) {
        return orderService.findByKeyword(keyword);
    }

    @GetMapping({"orders/byCustomerId/{customerId}", "/customers/{customerId}/orders"})
    public List<Order> findByCustomerId(@PathVariable int customerId) {
        return orderService.findByCustomerId(customerId);
    }

    @GetMapping({"orders/byCustomerId/{customerId}/{keyword}", "/customers/{customerId}/orders/{keyword}"})
    public List<Order> findByKeyword(@PathVariable int customerId, @PathVariable String keyword) {
        return orderService.findByCustomerIdKeyword(customerId, keyword);
    }

    @PutMapping("orders/{orderId}")
    public Order update(@RequestBody Order order, @PathVariable int orderId) {
        order.setOrderId(orderId);
        orderService.update(order);
        return orderService.findById(order.getOrderId());
    }


    @DeleteMapping("orders/{orderId}")
    public String deleteById(@PathVariable int orderId) {
        orderService.deleteById(orderId);
        return "Order with id " + orderId + " has been deleted";
    }


    @DeleteMapping({"orders/byCustomerId/{customerId}", "customers/{customerId}/orders"})
    public String deleteByCustomerId(@PathVariable int customerId) {
        orderService.deleteByCustomerId(customerId);
        return "Orders of customer with id " + customerId + " have been deleted";
    }

    @DeleteMapping("orders")
    public String deleteAll() {
        orderService.deleteAll();
        return "All orders have been deleted";
    }
}
