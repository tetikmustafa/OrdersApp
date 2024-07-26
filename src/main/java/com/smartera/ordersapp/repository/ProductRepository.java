package com.smartera.ordersapp.repository;

import com.smartera.ordersapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByProductNameContainingOrProductDescriptionContaining(String keyword, String keyword1);
}
