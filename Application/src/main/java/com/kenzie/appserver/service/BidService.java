package com.kenzie.appserver.service;
import com.kenzie.appserver.repositories.BidRepository;
import com.kenzie.appserver.repositories.model.BidRecord;
import com.kenzie.appserver.service.model.Bid;
import com.kenzie.appserver.service.model.Buyer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidService {

    private BidRepository bidRepository;

    public BidService(BidRepository bidRepository){
        this.bidRepository = bidRepository;
    }

    public void makeABid(Bid bid){
        if (bidRepository.existsById(bid.getBuyerId())) {
            BidRecord bidRecord = new BidRecord();
            bidRecord.setBuyerId(bid.getBuyerId());
            bidRecord.setBuyerName(bid.getBuyerName());
            bidRecord.setVehicleId(bid.getVehicleId());
            bidRecord.setBidPrice(bid.getBidPrice());
            bidRepository.save(bidRecord);
        }
    }

    public List<Bid> findAllBids(String buyerId){
        //Buyer buyer = findBuyerById(buyerId);
        //return buyer.getBidList();
        return new ArrayList<>();
    }

}
