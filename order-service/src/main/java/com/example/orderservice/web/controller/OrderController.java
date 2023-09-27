package com.example.orderservice.web.controller;

import com.example.orderservice.domain.Order;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.web.dto.OrderDto;
import com.example.orderservice.web.dto.RequestOrder;
import com.example.orderservice.web.dto.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/health_check")
    public String status() {
        return "start order-service";
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable String userId, @RequestBody RequestOrder requestOrder) {
        OrderDto orderDto = OrderDto.createOrderDto(requestOrder);
        orderDto.setUserId(userId);
        OrderDto order = orderService.createOrder(orderDto);


        ResponseOrder responseOrder = ResponseOrder.createResponseOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable String userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        List<ResponseOrder> result = new ArrayList<>();
        for(Order o : orders) {
            result.add(ResponseOrder.createResponseOrder(OrderDto.createOrderDto(o)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
