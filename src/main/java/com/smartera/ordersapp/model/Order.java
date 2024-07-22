package com.smartera.ordersapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private int orderCustomerId;
    private String orderName;
    private String orderDescription;
    @ElementCollection
    private List<Integer> orderProductsIds;
}
