package com.home;

public class AddressManager {
    private static Address address;

    public static void setAddress(Address addr) {
        address = addr;
    }

    public static Address getAddress() {
        return address;
    }
}

