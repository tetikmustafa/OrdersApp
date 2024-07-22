package com.smartera.ordersapp.controller;

import com.smartera.ordersapp.model.Product;
import com.smartera.ordersapp.service.IService;
import com.smartera.ordersapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController implements IController<Product> {

    @Autowired
    ProductService productService;

    @PostMapping("products")
    public Product save(@RequestBody Product product){
        productService.save(product);
        return productService.findById(product.getProductId());
    }

    @GetMapping("products/{productId}")
    public Product findById(@PathVariable int productId){
        return productService.findById(productId);
    }

    @GetMapping("products")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("products/keyword/{keyword}")
    public List<Product> findByKeyword(@PathVariable String keyword){
        return productService.findByKeyword(keyword);
    }

    @PutMapping("products/{productId}")
    public Product update(@RequestBody Product product,@PathVariable int productId){
        product.setProductId(productId);
        productService.update(product);
        return productService.findById(productId);
    }

    @DeleteMapping("products/{productId}")
    public String deleteById(@PathVariable int productId){
        productService.deleteById(productId);
        return "Product with id " + productId + " has been deleted";
    }

    @DeleteMapping("products")
    public String deleteAll(){
        productService.deleteAll();
        return "All products have been deleted";
    }
}
