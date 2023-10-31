package com.kiosk.testcodepractice.spring.domain.product.service;

import com.kiosk.testcodepractice.spring.domain.product.dto.ProductResponse;
import com.kiosk.testcodepractice.spring.domain.product.entity.Product;
import com.kiosk.testcodepractice.spring.domain.product.repository.ProductRepository;
import com.kiosk.testcodepractice.spring.domain.product.ProductSellingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts(){
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(product -> ProductResponse.of(product))
                .collect(Collectors.toList());
    }
}
