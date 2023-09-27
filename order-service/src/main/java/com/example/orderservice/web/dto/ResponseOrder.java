package com.example.orderservice.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;

    @Builder
    public ResponseOrder(String productId, Integer qty, Integer unitPrice, Integer totalPrice, String orderId) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
    }

    public static ResponseOrder createResponseOrder(OrderDto orderDto) {
        return ResponseOrder.builder()
                .productId(orderDto.getProductId())
                .qty(orderDto.getQty())
                .unitPrice(orderDto.getUnitPrice())
                .totalPrice(orderDto.getTotalPrice())
                .orderId(orderDto.getOrderId())
                .build();
    }
}
