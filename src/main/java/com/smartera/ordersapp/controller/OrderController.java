package com.smartera.ordersapp.controller;

import com.smartera.ordersapp.dto.order.OrderCreateDto;
import com.smartera.ordersapp.dto.order.OrderDto;
import com.smartera.ordersapp.dto.order.OrderIdDto;
import com.smartera.ordersapp.entity.Order;
import com.smartera.ordersapp.mapper.OrderMapper;
import com.smartera.ordersapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("orders")
public class OrderController{

    @Autowired
    OrderService orderService;

    @PostMapping()
    public OrderIdDto save(@RequestBody OrderCreateDto orderDto) {
        Order order = OrderMapper.toOrder(orderDto);
        orderService.save(order);
        return OrderMapper.toOrderIdDto(orderService.findById(order.getOrderId()));
    }

    @PostMapping("/byCustomerId/{customerId}")
    public OrderIdDto save(@RequestBody OrderCreateDto orderDto, @PathVariable UUID customerId) {
        Order order = OrderMapper.toOrder(orderDto);
        orderService.save(order, customerId);
        return OrderMapper.toOrderIdDto(orderService.findById(order.getOrderId()));
    }

    @GetMapping("/{orderId}")
    public OrderDto findById(@PathVariable UUID orderId) {
        return OrderMapper.toOrderDto(orderService.findById(orderId));
    }

    @GetMapping()
    public List<OrderDto> findAll() {
        return orderService.findAll()
                .stream().map(OrderMapper::toOrderDto).toList();
    }

    @GetMapping("/keyword/{keyword}")
    public List<OrderDto> findByKeyword(@PathVariable String keyword) {
        return orderService.findByKeyword(keyword)
                .stream().map(OrderMapper::toOrderDto).toList();
    }

    @GetMapping("/byCustomerId/{customerId}")
    public List<OrderDto> findByCustomerId(@PathVariable UUID customerId) {
        return orderService.findByCustomerId(customerId)
                .stream().map(OrderMapper::toOrderDto).toList();
    }

    @GetMapping("/byCustomerId/{customerId}/{keyword}")
    public List<OrderDto> findByKeyword(@PathVariable UUID customerId, @PathVariable String keyword) {
        return orderService.findByCustomerIdKeyword(customerId, keyword)
                .stream().map(OrderMapper::toOrderDto).toList();
    }

    @PutMapping("/{orderId}")
    public OrderIdDto update(@RequestBody OrderCreateDto orderDto, @PathVariable UUID orderId) {
        Order order = OrderMapper.toOrder(orderDto);
        order.setOrderId(orderId);
        orderService.update(order);
        return OrderMapper.toOrderIdDto(orderService.findById(orderId));
    }


    @DeleteMapping("/{orderId}")
    public String deleteById(@PathVariable UUID orderId) {
        orderService.deleteById(orderId);
        return "Order with id " + orderId + " has been deleted";
    }


    @DeleteMapping("/byCustomerId/{customerId}")
    public String deleteByCustomerId(@PathVariable UUID customerId) {
        orderService.deleteByCustomerId(customerId);
        return "Orders of customer with id " + customerId + " have been deleted";
    }

    @DeleteMapping()
    public String deleteAll() {
        orderService.deleteAll();
        return "All orders have been deleted";
    }
}
