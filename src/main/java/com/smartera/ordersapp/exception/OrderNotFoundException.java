package com.smartera.ordersapp.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(int orderId) {
        super("Order not found : "+orderId);
    }
}
