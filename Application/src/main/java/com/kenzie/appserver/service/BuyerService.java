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


    public void makeABid(Buyer buyer, Vehicle vehicle, double price){

        Bid bid = new Bid();
        bid.setVehicleId(vehicle.getVehicleId());
        bid.setBidPrice(price);
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


    public List<Bid> getListofBids(String buyerId){

        Buyer buyerFromBackendService = buyerRepository
                .findById(buyerId)
                .map(buyer -> new Buyer(buyer.getId(),
                        buyer.getBuyerName(),
                        buyer.getBidList()))
                .orElse(null);

        List<Bid> bids = buyerFromBackendService.getBidList();

        return bids;

    }

}
