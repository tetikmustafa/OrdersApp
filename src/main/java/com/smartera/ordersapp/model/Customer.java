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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String customerName;
    private String customerDescription;
    private boolean customerAuthorization;
    @ElementCollection
    private List<Integer> customerOrdersIds;
}
