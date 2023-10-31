package com.kiosk.testcodepractice.spring.domain.order;

import com.kiosk.testcodepractice.spring.domain.order.entity.Order;
import com.kiosk.testcodepractice.spring.domain.product.entity.Product;
import com.kiosk.testcodepractice.spring.domain.product.ProductType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static com.kiosk.testcodepractice.spring.domain.product.ProductSellingStatus.SELLING;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderTest {

    @Test
    @DisplayName("주문 생성 시 상품리스트에서 주문의 총 금액을 계산한다")
    void calculateTotalPrice() throws Exception {
        //given
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );
        //when
        Order order = Order.createOrder(products, LocalDateTime.now());
        //then
        assertThat(order.getTotalPrice()).isEqualTo(3000);
    }

    @Test
    @DisplayName("주문 생성 시 주문 상태는 INIT 이다.")
    void INITWhenOrder() throws Exception {
        //given
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );
        //when
        Order order = Order.createOrder(products, LocalDateTime.now());
        //then
        assertThat(order.getOrderStatus()).isEqualByComparingTo(OrderStatus.INIT);  // enum 검증
    }

    @Test
    @DisplayName("주문 생성 시 주문 생성시간을 현재 시간으로 넣는다")
    void createOrderDateTime() throws Exception {
        //given
        LocalDateTime registerTime = LocalDateTime.now();
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );
        //when
        Order order = Order.createOrder(products, registerTime);
        //then
        assertThat(order.getRegisteredDateTime()).isEqualTo(registerTime);
    }
    


    private Product createProduct(String productNumber, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(ProductType.HANDMADE)
                .sellingStatus(SELLING)
                .name("아메리카노")
                .price(price)
                .build();
    }
}