package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.User;
import com.kenzie.appserver.service.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.UUID.randomUUID;

@DynamoDBTable(tableName = "SellerAccount")
public class SellerRecord extends User {

    private String userId;
    private String sellerName;
    public List<Vehicle> vehicleList; //List of vehicles for sale

    @DynamoDBHashKey (attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute (attributeName = "sellerName")
    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @DynamoDBAttribute (attributeName = "listVehicles")
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellerRecord that = (SellerRecord) o;
        return Objects.equals(userId, that.userId) && Objects.equals(sellerName, that.sellerName) && Objects.equals(vehicleList, that.vehicleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, sellerName, vehicleList);
    }
}
