package com.smartera.ordersapp.exception;

import java.util.UUID;

public class CustomerNotAuthorizedException extends RuntimeException{
    public CustomerNotAuthorizedException(UUID customerId) {
        super("Customer not authorized : "+customerId);
    }
}
