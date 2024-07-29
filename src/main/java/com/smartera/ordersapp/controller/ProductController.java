package com.smartera.ordersapp.controller;

import com.smartera.ordersapp.dto.product.ProductCreateDto;
import com.smartera.ordersapp.dto.product.ProductDto;
import com.smartera.ordersapp.dto.product.ProductIdDto;
import com.smartera.ordersapp.entity.Product;
import com.smartera.ordersapp.mapper.ProductMapper;
import com.smartera.ordersapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("products")
public class ProductController{

    @Autowired
    ProductService productService;

    @PostMapping()
    public ProductIdDto save(@RequestBody ProductCreateDto productDto){
        Product product = ProductMapper.toProduct(productDto);
        productService.save(product);
        return ProductMapper.toProductIdDto(product);
    }

    @GetMapping("/{productId}")
    public ProductDto findById(@PathVariable UUID productId){
        Product product = productService.findById(productId);
        return ProductMapper.toProductDto(product);
    }

    @GetMapping()
    public List<ProductDto> findAll(){
        return productService.findAll()
                .stream().map(ProductMapper::toProductDto).toList();
    }

    @GetMapping("/keyword/{keyword}")
    public List<ProductDto> findByKeyword(@PathVariable String keyword){
        return productService.findByKeyword(keyword)
                .stream().map(ProductMapper::toProductDto).toList();
    }

    @PutMapping("/{productId}")
    public ProductIdDto update(@RequestBody ProductCreateDto productDto,@PathVariable UUID productId){
        Product product = ProductMapper.toProduct(productDto);
        product.setProductId(productId);
        productService.update(product);
        return ProductMapper.toProductIdDto(product);
    }

    @DeleteMapping("/{productId}")
    public String deleteById(@PathVariable UUID productId){
        productService.deleteById(productId);
        return "Product with id " + productId + " has been deleted";
    }

    @DeleteMapping()
    public String deleteAll(){
        productService.deleteAll();
        return "All products have been deleted";
    }
}
