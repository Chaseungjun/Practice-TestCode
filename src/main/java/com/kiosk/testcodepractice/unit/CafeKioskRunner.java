package com.kiosk.testcodepractice.unit;

import com.kiosk.testcodepractice.unit.beverage.Americano;
import com.kiosk.testcodepractice.unit.beverage.Latte;

public class CafeKioskRunner {

    public static void main(String[] args) {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        cafeKiosk.add(new Latte());

        int totalPrice = cafeKiosk.calculateTotalPrice();
    }
}
