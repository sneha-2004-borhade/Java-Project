package com.home;

public class CouponUtil {
    public static int getDiscountAmount(String priceText) {
        try {
            int price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
            return (int) (price * 0.15); // 15% discount
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
