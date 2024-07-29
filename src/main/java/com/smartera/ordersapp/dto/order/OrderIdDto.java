package com.smartera.ordersapp.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderIdDto {
    private UUID orderId;
    private UUID orderCustomerId;
}
