package com.smartera.ordersapp.service.impl;

import com.smartera.ordersapp.exception.ProductNotFoundException;
import com.smartera.ordersapp.entity.Product;
import com.smartera.ordersapp.repository.ProductRepository;
import com.smartera.ordersapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product findById(UUID productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product>  findByKeyword(String keyword){
        return productRepository.findByProductNameContainingOrProductDescriptionContaining(keyword, keyword);
    }


    public void update(Product product) {
        Optional<Product> o = productRepository.findById(product.getProductId());
        if (o.isEmpty()) {
            throw new ProductNotFoundException(product.getProductId());
        }
        productRepository.save(product);

    }

    public void deleteById(UUID productId){
        Optional<Product> o = productRepository.findById(productId);
        if (o.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }
        productRepository.deleteById(productId);
    }

    public void deleteAll(){
        productRepository.deleteAll();
    }
}
