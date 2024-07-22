package com.smartera.ordersapp.repository;

import com.smartera.ordersapp.model.Customer;
import com.smartera.ordersapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    List<Customer> findByCustomerNameContainingOrCustomerDescriptionContaining(String keyword, String keyword1);
}
