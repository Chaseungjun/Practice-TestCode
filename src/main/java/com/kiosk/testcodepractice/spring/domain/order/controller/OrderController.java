package com.kiosk.testcodepractice.spring.domain.order.controller;

import com.kiosk.testcodepractice.spring.domain.order.dto.OrderCreateRequest;
import com.kiosk.testcodepractice.spring.domain.order.dto.OrderResponse;
import com.kiosk.testcodepractice.spring.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public OrderResponse createOrder(@RequestBody OrderCreateRequest request){
        LocalDateTime registeredDateTime = LocalDateTime.now();
       return orderService.createOrder(request, registeredDateTime);
    }
}
