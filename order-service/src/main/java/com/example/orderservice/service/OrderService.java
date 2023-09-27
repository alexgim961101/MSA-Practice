package com.example.orderservice.service;

import com.example.orderservice.domain.Order;
import com.example.orderservice.web.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    List<Order> getOrdersByUserId(String userId);
}
