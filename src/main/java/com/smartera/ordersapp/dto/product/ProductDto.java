package com.smartera.ordersapp.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductDto {
    private UUID productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productStock;
}
