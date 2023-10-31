package com.kiosk.testcodepractice.spring.api.service.order;

import com.kiosk.testcodepractice.spring.domain.order.dto.OrderCreateRequest;
import com.kiosk.testcodepractice.spring.domain.order.dto.OrderResponse;
import com.kiosk.testcodepractice.spring.domain.order.repository.OrderRepository;
import com.kiosk.testcodepractice.spring.domain.order.service.OrderService;
import com.kiosk.testcodepractice.spring.domain.orderproduct.repository.OrderProductRepository;
import com.kiosk.testcodepractice.spring.domain.product.entity.Product;
import com.kiosk.testcodepractice.spring.domain.product.repository.ProductRepository;
import com.kiosk.testcodepractice.spring.domain.product.ProductType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static com.kiosk.testcodepractice.spring.domain.product.ProductSellingStatus.SELLING;
import static com.kiosk.testcodepractice.spring.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderService orderService;

    @AfterEach  // 테스트가 끝나고 데이터를 지워준다
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("주문번호 리스트를 받아 주문을 생성한다")
    void createOrder() throws Exception {
        //given
        Product product1 = createProduct("001", HANDMADE, 1000);
        Product product2 = createProduct("002", HANDMADE, 2000);
        Product product3 = createProduct("003", HANDMADE, 3000);
        List<Product> productList = List.of(product1, product2, product3);
        productRepository.saveAll(productList);

        OrderCreateRequest request = OrderCreateRequest.builder()
                .productNumbers(List.of("001", "002"))
                .build();

        //when
        LocalDateTime registedTime = LocalDateTime.now();
        OrderResponse orderResponse = orderService.createOrder(request, registedTime);

        //then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(registedTime, 3000);
        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 1000),
                        tuple("002", 2000)
                );
    }

    @Test
    @DisplayName("중복되는 상품번호 리스트로 주문을 생성할 수 있다")
    void createOrderWithDuplicateProductNumber() throws Exception {
        //given
        Product product1 = createProduct("001", HANDMADE, 1000);
        Product product2 = createProduct("002", HANDMADE, 2000);
        Product product3 = createProduct("003", HANDMADE, 3000);
        List<Product> productList = List.of(product1, product2, product3);
        productRepository.saveAll(productList);

        OrderCreateRequest request = OrderCreateRequest.builder()
                .productNumbers(List.of("001", "001"))
                .build();

        //when
        LocalDateTime registedTime = LocalDateTime.now();
        OrderResponse orderResponse = orderService.createOrder(request, registedTime);

        //then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
                .extracting("registeredDateTime", "totalPrice")
                .contains(registedTime, 2000);
        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 1000),
                        tuple("001", 1000)
                );
    }

    private Product createProduct(String productNumber, ProductType productType, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(productType)
                .sellingStatus(SELLING)
                .name("아메리카노")
                .price(price)
                .build();
    }

}