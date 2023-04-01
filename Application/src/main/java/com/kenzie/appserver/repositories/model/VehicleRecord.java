package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "Vehicles")
public class VehicleRecord {

    private String vehicleId;
    private String make;
    private String model;
    private String year;
    private String price;

    @DynamoDBHashKey(attributeName = "id")
    public String getVehicleId() {return this.vehicleId; }

    public void setVehicleId(String vehicleId) {this.vehicleId = vehicleId; }

    @DynamoDBAttribute(attributeName = "make")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @DynamoDBAttribute(attributeName = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    @DynamoDBAttribute(attributeName = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @DynamoDBAttribute(attributeName = "price")
    public String getPrice() {return this.price; }

    public void setPrice(String price) {this.price = price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleRecord that = (VehicleRecord) o;
        return Objects.equals(vehicleId, that.vehicleId) && Objects.equals(make, that.make) && Objects.equals(model, that.model) && Objects.equals(year, that.year) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, make, model, year, price);
    }
}
