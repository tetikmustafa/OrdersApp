package com.smartera.ordersapp.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(int productId) {
        super("Product not found : "+productId);
    }
}
