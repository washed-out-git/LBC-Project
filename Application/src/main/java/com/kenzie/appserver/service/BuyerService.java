package com.kenzie.appserver.service;

<<<<<<< HEAD
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
        buyerRecord.setBidList(buyer.getBidList());
        buyerRepository.save(buyerRecord);
        return buyer;
    }

    public Buyer findBuyerById(String buyerId) {

        // if not cached, find the concert
        Buyer buyerFromBackendService = buyerRepository
                .findById(buyerId)
                .map(buyer -> new Buyer(buyer.getBuyerName()))
                .orElse(null);
        // return concert
        return buyerFromBackendService;
    }


    public void makeABid(Buyer buyer, Bid bid){

        List<Bid> bidList = buyer.getBidList();
        bidList.add(bid);

        BuyerRecord buyerRecord = new BuyerRecord();
        buyerRecord.setId(buyer.getUserId());
        buyerRecord.setBuyerName(buyer.getBuyerName());
        buyerRecord.setBidList(bidList);
        buyerRepository.save(buyerRecord);
    }

    public List<Vehicle> getListofCars(){
        return new ArrayList<>();
    }


=======
public class BuyerService {
>>>>>>> main
}
