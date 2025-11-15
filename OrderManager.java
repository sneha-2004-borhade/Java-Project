

package com.home;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private static List<Product> orders = new ArrayList<>();

    public static void addOrder(Image image, String title, int days) {
    Product product = new Product(image, title, days);
    product.setSyncedToFirebase(false); // ✅ New line
    orders.add(product);
    System.out.println("🛒 Added to OrderManager: " + title + " - " + days + " day(s)");
}

    
    public static void addOrder(Product product) {
        orders.add(product);
        System.out.println("🛒 Added to OrderManager: " + product.getTitle() + " - " + product.getDays() + " day(s)");
    }

 
    public static List<Product> getOrders() {
        return new ArrayList<>(orders); 
    }

    
    public static void clearOrders() {
       
    }
}

