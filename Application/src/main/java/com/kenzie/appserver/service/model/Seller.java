package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.List;

public class Seller {

    private final String userId;
    private String sellerName;
    public List<Vehicle> vehicleList;

    public Seller(String sellerEmail, String sellerName) {
        this.userId = sellerEmail;
        this.sellerName = sellerName;
        this.vehicleList = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
