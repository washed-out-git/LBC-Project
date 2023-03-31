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
        buyerRecord.setBidList(buyer.getBidList());
        buyerRepository.save(buyerRecord);
        return buyer;
    }

   public Buyer findBuyerById(String buyerId) {

        Buyer buyerFromBackendService = buyerRepository
                .findById(buyerId)
                .map(buyer -> new Buyer(buyer.getId(),
                        buyer.getBuyerName(),
                        buyer.getBidList()))
                .orElse(null);
        return buyerFromBackendService;
    }


    public void makeABid(String buyerId, Bid bid){

        Buyer buyerFromRepo = findBuyerById(buyerId);
        List<Bid> bidList = buyerFromRepo.getBidList();
        bidList.add(bid);

        if (buyerRepository.existsById(buyerId)) {

            BuyerRecord buyerRecord = new BuyerRecord();
            buyerRecord.setId(buyerFromRepo.getUserId());
            buyerRecord.setBuyerName(buyerFromRepo.getBuyerName());
            buyerRecord.setBidList(bidList);
            buyerRepository.save(buyerRecord);
        }
    }

    public List<Vehicle> getListofCars(){
        return new ArrayList<>();
    }

    public List<Bid> findAllBids(String buyerId){
        Buyer buyer = findBuyerById(buyerId);
        return buyer.getBidList();
    }

}
