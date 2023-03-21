package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "Buyer")
public class BuyerRecord {

    private String id;
    private String buyerName;
    public List<String> bidList;

    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return id;
    }

    @DynamoDBAttribute(attributeName = "buyerName")
    public String getBuyerName() {
        return buyerName;
    }

    public List<String> getBidList() {
        return bidList;
    }
}
