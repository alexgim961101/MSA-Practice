package com.example.orderservice.service;

import com.example.orderservice.domain.Order;
import com.example.orderservice.domain.OrderRepo;
import com.example.orderservice.web.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepo orderRepo;
    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
        orderDetails.setOrderId(UUID.randomUUID().toString());
        orderDetails.setTotalPrice(orderDetails.getQty() * orderDetails.getUnitPrice());
        Order order = Order.createOrder(orderDetails);
        Order orderEntity = orderRepo.save(order);

        return OrderDto.createOrderDto(orderEntity);
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        return OrderDto.createOrderDto(orderRepo.findByOrderId(orderId).get());
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return orderRepo.findAllByUserId(userId);
    }
}
