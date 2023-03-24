package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.Bid;

import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.UUID.randomUUID;

@DynamoDBTable(tableName = "Buyer")
public class BuyerRecord {

    private String id;
    private String buyerName;
    public List<Bid> bidList;

    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return id;
    }

    @DynamoDBAttribute(attributeName = "buyerName")
    public String getBuyerName() {
        return buyerName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }


    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }


}