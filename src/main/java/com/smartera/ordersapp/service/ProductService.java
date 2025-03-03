package com.smartera.ordersapp.service;

import com.smartera.ordersapp.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void save(Product product);
    Product findById(UUID productId);
    List<Product> findAll();
    List<Product> findByKeyword(String keyword);
    void update(Product product);
    void deleteById(UUID productId);
    void deleteAll();

}
