package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "Vehicles")
public class VehicleRecord {

    private String make;
    private String model;
    private int year;
    private boolean isAvailable;
    private String vehicleId;
    @DynamoDBHashKey(attributeName = "make")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
    @DynamoDBIndexRangeKey(attributeName = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    @DynamoDBAttribute(attributeName = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    @DynamoDBAttribute(attributeName = "available")
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    @DynamoDBAttribute
    public String getVehicleId() {return this.vehicleId; }
    public void setVehicleId(String vehicleId) {this.vehicleId = vehicleId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleRecord that = (VehicleRecord) o;
        return year == that.year && isAvailable == that.isAvailable && make.equals(that.make) && model.equals(that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year, isAvailable);
    }
}
