package com.kiosk.testcodepractice.spring.domain.product.controller;

import com.kiosk.testcodepractice.spring.domain.product.service.ProductService;
import com.kiosk.testcodepractice.spring.domain.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class productController {

    private final ProductService productService;

    @GetMapping("/api/v1/products/selling")
    public List<ProductResponse> getSellingProducts(){
        return productService.getSellingProducts();
    }
}
