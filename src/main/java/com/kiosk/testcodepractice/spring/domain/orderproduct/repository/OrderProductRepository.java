package com.kiosk.testcodepractice.spring.domain.orderproduct.repository;

import com.kiosk.testcodepractice.spring.domain.orderproduct.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
