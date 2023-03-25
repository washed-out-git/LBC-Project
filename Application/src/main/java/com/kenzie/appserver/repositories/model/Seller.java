package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.repositories.model.User;
import com.kenzie.appserver.service.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

@DynamoDBTable(tableName = "SellerAccount")
public class Seller extends User {

    private final String userId;
    private String sellerName;
    public List<Vehicle> vehicleList; //List of vehicles for sale

    public Seller(String sellerName) {
        this.sellerName = sellerName;
        this.userId = randomUUID().toString();
        this.vehicleList = new ArrayList<Vehicle>();
    }

    @DynamoDBHashKey (attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    @DynamoDBRangeKey (attributeName = "sellerName")
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
}
