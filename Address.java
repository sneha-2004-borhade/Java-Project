package com.home;

public class Address {
    private String name;
    private String phone;
    private String street;
    private String landmark;
    private String district;
    private String state;
    private String pincode;

    public Address(String name, String phone, String street, String landmark, String district, String state, String pincode) {
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.landmark = landmark;
        this.district = district;
        this.state = state;
        this.pincode = pincode;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getStreet() { return street; }
    public String getLandmark() { return landmark; }
    public String getDistrict() { return district; }
    public String getState() { return state; }
    public String getPincode() { return pincode; }

    public String getFullAddress() {
        return street + ", " + landmark + ", " + district + ", " + state + " - " + pincode;
    }
}

