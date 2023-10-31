package com.kiosk.testcodepractice.spring.domain.product.repository;

import com.kiosk.testcodepractice.spring.domain.product.ProductSellingStatus;
import com.kiosk.testcodepractice.spring.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);

    List<Product> findAllByProductNumberIn(List<String> products);  // In 에 중복제거 기능이 들어있다.
}
