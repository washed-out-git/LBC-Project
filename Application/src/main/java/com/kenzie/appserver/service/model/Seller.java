package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

public class Seller extends User {

    private final String userId;
    private String sellerName;
    public List<Vehicle> vehicleList; //List of vehicles for sale

    public Seller(String sellerName) {
        this.sellerName = sellerName;
        this.userId = randomUUID().toString();
        this.vehicleList = new ArrayList<Vehicle>();
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
