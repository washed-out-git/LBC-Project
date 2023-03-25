package com.kenzie.appserver.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.repositories.model.Seller;

import javax.inject.Inject;
import java.util.Objects;

public class SellerDao {

    private DynamoDBMapper mapper;

    @Inject
    public void sellerDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public Seller createSeller(Seller seller){
        if(seller.getSellerName() == null){
            throw new IllegalArgumentException("Seller Name can not be null");
        }
        mapper.save(seller);
        return seller;
    }

    public Seller deleteSeller(String sellerId){
        //should we allow deleting user accounts? or add a column for active/deactivated accounts? -- extra feature
        return null;
    }
}
