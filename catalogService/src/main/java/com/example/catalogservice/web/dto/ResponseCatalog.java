package com.example.catalogservice.web.dto;

import com.example.catalogservice.domain.Catalog;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDateTime createdAt;

    @Builder
    public ResponseCatalog(String productId, String productName, Integer unitPrice, Integer totalPrice, LocalDateTime createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }


    public static ResponseCatalog createResponse(Catalog catalog) {
        return ResponseCatalog.builder()
                .productId(catalog.getProductId())
                .productName(catalog.getProductName())
                .unitPrice(catalog.getUnitPrice())
                .totalPrice(catalog.getUnitPrice() * catalog.getStock())
                .createdAt(catalog.getCreatedAt())
                .build();
    }
}
