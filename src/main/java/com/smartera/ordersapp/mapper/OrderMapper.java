package com.smartera.ordersapp.mapper;

import com.smartera.ordersapp.dto.order.OrderCreateDto;
import com.smartera.ordersapp.dto.order.OrderDto;
import com.smartera.ordersapp.dto.order.OrderIdDto;
import com.smartera.ordersapp.entity.Order;

public class OrderMapper {
    public static OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setOrderCustomerId(order.getOrderCustomerId());
        orderDto.setOrderName(order.getOrderName());
        orderDto.setOrderDescription(order.getOrderDescription());
        orderDto.setOrderProductsIds(order.getOrderProductsIds());
        return orderDto;
    }

    public static Order toOrder(OrderCreateDto orderCreateDto) {
        Order order = new Order();
        order.setOrderName(orderCreateDto.getOrderName());
        order.setOrderDescription(orderCreateDto.getOrderDescription());
        order.setOrderProductsIds(orderCreateDto.getOrderProductsIds());
        return order;
    }

    public static OrderIdDto toOrderIdDto(Order order) {
        OrderIdDto orderIdDto = new OrderIdDto();
        orderIdDto.setOrderId(order.getOrderId());
        orderIdDto.setOrderCustomerId(order.getOrderCustomerId());
        return orderIdDto;
    }
}
