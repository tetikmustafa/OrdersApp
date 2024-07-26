package com.smartera.ordersapp.repository;

import com.smartera.ordersapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByOrderCustomerId(int customerId);

    List<Order> findByOrderCustomerIdAndOrderDescriptionContaining(int customerId, String keyword);

    List<Order> findByOrderNameContainingOrOrderDescriptionContaining(String keyword, String keyword1);
}
