package com.smartera.ordersapp.controller;

import com.smartera.ordersapp.model.Product;
import com.smartera.ordersapp.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController{

    @Autowired
    ProductServiceImpl productServiceImpl;

    @PostMapping("products")
    public Product save(@RequestBody Product product){
        productServiceImpl.save(product);
        return productServiceImpl.findById(product.getProductId());
    }

    @GetMapping("products/{productId}")
    public Product findById(@PathVariable int productId){
        return productServiceImpl.findById(productId);
    }

    @GetMapping("products")
    public List<Product> findAll(){
        return productServiceImpl.findAll();
    }

    @GetMapping("products/keyword/{keyword}")
    public List<Product> findByKeyword(@PathVariable String keyword){
        return productServiceImpl.findByKeyword(keyword);
    }

    @PutMapping("products/{productId}")
    public Product update(@RequestBody Product product,@PathVariable int productId){
        product.setProductId(productId);
        productServiceImpl.update(product);
        return productServiceImpl.findById(productId);
    }

    @DeleteMapping("products/{productId}")
    public String deleteById(@PathVariable int productId){
        productServiceImpl.deleteById(productId);
        return "Product with id " + productId + " has been deleted";
    }

    @DeleteMapping("products")
    public String deleteAll(){
        productServiceImpl.deleteAll();
        return "All products have been deleted";
    }
}
