package com.example.orderservice.domain;

import com.example.orderservice.web.dto.OrderDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String productId;
    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String userId;
    @Column(unique = true, nullable = false)
    private String orderId;

    @Column(updatable = false, insertable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Order(Long id, String productId, Integer qty, Integer unitPrice, Integer totalPrice, String userId, String orderId, LocalDateTime createdAt) {
        this.id = id;
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.orderId = orderId;
        this.createdAt = createdAt;
    }

    public static Order createOrder(OrderDto dto){
        return Order.builder()
                .productId(dto.getProductId())
                .qty(dto.getQty())
                .unitPrice(dto.getUnitPrice())
                .totalPrice(dto.getTotalPrice())
                .userId(dto.getUserId())
                .orderId(dto.getOrderId())
                .build();
    }
}
