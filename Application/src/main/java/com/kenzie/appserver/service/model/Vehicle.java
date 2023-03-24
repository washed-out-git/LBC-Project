package com.kenzie.appserver.service.model;

public class Vehicle {

    private String make;
    private String model;
    private int year;
    private boolean isAvailable;
    private String vehicleId;

    public Vehicle(String make, String model, int year, boolean isAvailable, String vehicleId) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.isAvailable = isAvailable;
        this.vehicleId = vehicleId;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getVehicleId() {return this.vehicleId; }
    public void setVehicleId(String vehicleId) {this.vehicleId = vehicleId; }

}