package com.kiosk.testcodepractice.spring.domain.product.dto;

import com.kiosk.testcodepractice.spring.domain.product.entity.Product;
import com.kiosk.testcodepractice.spring.domain.product.ProductSellingStatus;
import com.kiosk.testcodepractice.spring.domain.product.ProductType;
import lombok.Builder;

public class ProductResponse {

    private Long id;
    private String productNumber;
    private ProductType type;
    private ProductSellingStatus sellingStatus;
    private String name;
    private int price;


    @Builder
    private ProductResponse(Long id, String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.id = id;
        this.productNumber = productNumber;
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productNumber(product.getProductNumber())
                .type(product.getType())
                .sellingStatus(product.getSellingStatus())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
