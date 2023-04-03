package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
@DynamoDBTable(tableName = "Bids")
public class BidRecord {

    private String buyerId;
    private String bidId;
    private String buyerName;
    private String vehicleId;
    private String dateOfBid;

    public double bidPrice;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "buyerId", attributeName = "buyerId")
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

    @DynamoDBAttribute(attributeName = "dateOfBid")
    public String getDateOfBid() {
        return dateOfBid;
    }

    public void setDateOfBid(String dateOfBid) {
        this.dateOfBid = dateOfBid;
    }
    @DynamoDBHashKey(attributeName = "bidId")
    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
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
