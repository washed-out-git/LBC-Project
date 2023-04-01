package com.kenzie.appserver.service.model;

public class
Vehicle {

    private String make;
    private String model;
    private String year;
    private String vehicleId;
    private String price;
    private String sellerId;

    public Vehicle(String vehicleId, String make, String model, String year, String price, String sellerId) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.vehicleId = vehicleId;
        this.price = price;
        this.sellerId = sellerId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVehicleId() {return this.vehicleId; }

    public void setVehicleId(String vehicleId) {this.vehicleId = vehicleId; }

    public String getPrice() {return this.price; }

    public void setPrice(String price) {this.price = price; }

    public String getSellerId() {return this.sellerId; }

    public void setSellerId(String sellerId) {this.sellerId = sellerId; }

}
