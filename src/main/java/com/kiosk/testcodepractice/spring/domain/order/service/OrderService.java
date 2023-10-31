package com.kiosk.testcodepractice.spring.domain.order.service;

import com.kiosk.testcodepractice.spring.domain.order.dto.OrderCreateRequest;
import com.kiosk.testcodepractice.spring.domain.order.dto.OrderResponse;
import com.kiosk.testcodepractice.spring.domain.order.entity.Order;
import com.kiosk.testcodepractice.spring.domain.order.repository.OrderRepository;
import com.kiosk.testcodepractice.spring.domain.product.entity.Product;
import com.kiosk.testcodepractice.spring.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {
        List<String> productNumbers = request.getProductNumbers();

        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

        Order order = Order.createOrder(products, registeredDateTime);
        Order savedOrder = orderRepository.save(order);
        return OrderResponse.of(savedOrder);

    }

}
