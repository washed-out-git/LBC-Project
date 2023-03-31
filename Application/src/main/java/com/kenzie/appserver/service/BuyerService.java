package com.kenzie.appserver.service;
import com.kenzie.appserver.repositories.BuyerRepository;

import com.kenzie.appserver.repositories.model.BuyerRecord;
import com.kenzie.appserver.service.model.Bid;
import com.kenzie.appserver.service.model.Buyer;
import com.kenzie.appserver.service.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyerService {
    private BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository){
        this.buyerRepository = buyerRepository;
    }

    public Buyer addNewBuyer(Buyer buyer) {
        BuyerRecord buyerRecord = new BuyerRecord();
        buyerRecord.setId(buyer.getUserId());
        buyerRecord.setBuyerName(buyer.getBuyerName());
        buyerRepository.save(buyerRecord);
        return buyer;
    }

   public Buyer findBuyerById(String buyerId) {

        Buyer buyerFromBackendService = buyerRepository
                .findById(buyerId)
                .map(buyer -> new Buyer(buyer.getId(),
                        buyer.getBuyerName()))
                .orElse(null);
        return buyerFromBackendService;
    }


}
