package com.smartera.ordersapp.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(int customerId) {
        super("Customer not found : "+customerId);
    }
}
