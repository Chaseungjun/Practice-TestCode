package com.kiosk.testcodepractice.unit.order;

import com.kiosk.testcodepractice.unit.beverage.Beverage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Order {

    public final LocalDateTime orderDateTime;
    public final List<Beverage> beverages;
}
