package com.smartera.ordersapp.service;

import com.smartera.ordersapp.model.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);
    Product findById(int productId);
    List<Product> findAll();
    List<Product> findByKeyword(String keyword);
    void update(Product product);
    void deleteById(int productId);
    void deleteAll();

}
