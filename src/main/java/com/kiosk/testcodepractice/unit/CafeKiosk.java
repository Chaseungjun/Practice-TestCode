package com.kiosk.testcodepractice.unit;

import com.kiosk.testcodepractice.unit.beverage.Beverage;
import com.kiosk.testcodepractice.unit.order.Order;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeKiosk {

    private static final LocalTime SHOP_OPEN_TIME = LocalTime.of(10,0);
    private static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22,0);
    private List<Beverage> beverages = new ArrayList<>();

    public Order createOrder(LocalDateTime currentDateTime){
//        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();
        if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)){
            throw new IllegalArgumentException("주문 가능한 시간이 아닙니다.");
        }
        return new Order(currentDateTime, beverages);
    }

    public void add(Beverage beverage) {
        beverages.add(beverage);
    }
    public void add(Beverage beverage, int count) {
        if (count <= 0){
            throw new IllegalArgumentException("1잔 이상 주문이 가능합니다");
        }
        for (int i = 0; i < count; i++) {
            beverages.add(beverage);
        }
    }

    public void remove(Beverage beverage){
        beverages.remove(beverage);
    }

    public void clear(){
        beverages.clear();
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Beverage beverage : beverages) {
            totalPrice += beverage.getPrice();
        }
        return totalPrice;
    }
}
