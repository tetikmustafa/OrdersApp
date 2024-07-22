package com.smartera.ordersapp.exception;

public class CustomerNotAuthorizedException extends RuntimeException{
    public CustomerNotAuthorizedException(int customerId) {
        super("Customer not authorized : "+customerId);
    }
}
