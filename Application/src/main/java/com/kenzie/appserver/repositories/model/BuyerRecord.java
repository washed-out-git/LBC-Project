package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.util.Objects;


@DynamoDBTable(tableName = "Buyer")
public class BuyerRecord {

    private String id;
    private String buyerName;

    @DynamoDBHashKey(attributeName = "buyerId")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyerRecord that = (BuyerRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(buyerName, that.buyerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyerName);
    }
}
