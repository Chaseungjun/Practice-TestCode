package com.kiosk.testcodepractice.spring.domain.order.repository;

import com.kiosk.testcodepractice.spring.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
