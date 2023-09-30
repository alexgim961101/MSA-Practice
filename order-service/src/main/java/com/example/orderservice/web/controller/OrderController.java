package com.example.orderservice.web.controller;

import com.example.orderservice.domain.Order;
import com.example.orderservice.mq.KafkaProducer;
import com.example.orderservice.mq.OrderProducer;
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
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {
    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;
    private final OrderProducer orderProducer;

    @GetMapping("/health_check")
    public String status() {
        return "start order-service";
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable String userId, @RequestBody RequestOrder requestOrder) {
        OrderDto orderDto = OrderDto.createOrderDto(requestOrder);
        orderDto.setUserId(userId);
        // OrderDto order = orderService.createOrder(orderDto);

        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(requestOrder.getQty() * requestOrder.getUnitPrice());

        kafkaProducer.send("example-catalog-topic", orderDto);
        orderProducer.send("orders", orderDto);

        ResponseOrder responseOrder = ResponseOrder.createResponseOrder(orderDto);
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
