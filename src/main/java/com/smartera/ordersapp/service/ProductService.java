package com.smartera.ordersapp.service;

import com.smartera.ordersapp.exception.ProductNotFoundException;
import com.smartera.ordersapp.model.Product;
import com.smartera.ordersapp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IService<Product> {

    @Autowired
    ProductRepo productRepo;

    public void save(Product product) {
        productRepo.save(product);
    }

    public Product findById(int productId) {
        return productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    public List<Product> findAll(){
        return productRepo.findAll();
    }

    public List<Product>  findByKeyword(String keyword){
        return productRepo.findByProductNameContainingOrProductDescriptionContaining(keyword, keyword);
    }


    public void update(Product product) {
        Optional<Product> o = productRepo.findById(product.getProductId());
        if (o.isEmpty()) {
            throw new ProductNotFoundException(product.getProductId());
        }
        productRepo.save(product);

    }

    public void deleteById(int productId){
        Optional<Product> o = productRepo.findById(productId);
        if (o.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }
        productRepo.deleteById(productId);
    }

    public void deleteAll(){
        productRepo.deleteAll();
    }
}
