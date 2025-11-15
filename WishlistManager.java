package com.home;

import java.util.ArrayList;
import java.util.List;

public class WishlistManager {
    private static List<Product> wishlistItems = new ArrayList<>();

    public static void addToWishlist(Product product) {
        wishlistItems.add(product);
    }

    public static void removeFromWishlist(Product product) {
        wishlistItems.remove(product);
    }

    public static List<Product> getWishlistItems() {
        return wishlistItems;
    }

    public static void clearWishlist() {
        wishlistItems.clear();
    }
}
