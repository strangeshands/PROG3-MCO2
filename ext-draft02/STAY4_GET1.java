public class STAY4_GET1 {
    private final String discountCode = "STAY4_GET1";

    public STAY4_GET1() {

    }

    public float applyDiscount(float basePrice, int daysLength) {
        return basePrice * (daysLength - 1);
    }
}