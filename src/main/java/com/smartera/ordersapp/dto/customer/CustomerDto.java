package com.smartera.ordersapp.dto.customer;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CustomerDto {
    private UUID customerId;
    private String customerName;
    private String customerDescription;
    private boolean customerAuthorization;
    private List<UUID> customerOrdersIds;
}
