package com.example.orderservice.web.dto;

import com.example.orderservice.domain.Order;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDto implements Serializable {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;

    @Builder
    protected OrderDto(String productId, Integer qty, Integer unitPrice, Integer totalPrice, String orderId, String userId) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
        this.userId = userId;
    }

    public static OrderDto createOrderDto(Order order) {
        return OrderDto.builder()
                .productId(order.getProductId())
                .qty(order.getQty())
                .unitPrice(order.getUnitPrice())
                .totalPrice(order.getTotalPrice())
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .build();
    }

    public static OrderDto createOrderDto(RequestOrder requestOrder) {
        return OrderDto.builder()
                .productId(requestOrder.getProductId())
                .qty(requestOrder.getQty())
                .unitPrice(requestOrder.getUnitPrice())
                .build();
    }
}
