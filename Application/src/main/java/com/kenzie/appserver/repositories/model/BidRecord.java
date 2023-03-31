package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
@DynamoDBTable(tableName = "Bid")
public class BidRecord {

    private String buyerId;
    private String buyerName;
    private String vehicleId;
    public double bidPrice;

    @DynamoDBHashKey(attributeName = "id")
    public String getBuyerId() {
        return buyerId;
    }

    @DynamoDBAttribute(attributeName = "buyerName")
    public String getBuyerName() {
        return buyerName;
    }

    @DynamoDBAttribute(attributeName = "vehicleId")
    public String getVehicleId() {
        return vehicleId;
    }

    @DynamoDBAttribute(attributeName = "bidPrice")
    public double getBidPrice() {
        return bidPrice;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
}
