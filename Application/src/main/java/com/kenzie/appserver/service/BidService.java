package com.kenzie.appserver.service;
import com.kenzie.appserver.repositories.BidRepository;
import com.kenzie.appserver.repositories.model.BidRecord;
import com.kenzie.appserver.service.model.Bid;
import com.kenzie.appserver.service.model.Buyer;
import com.kenzie.appserver.service.model.Seller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidService {

    private BidRepository bidRepository;

    public BidService(BidRepository bidRepository){
        this.bidRepository = bidRepository;
    }

    public Bid makeABid(Bid bid){
            BidRecord bidRecord = new BidRecord();
            bidRecord.setBuyerId(bid.getBuyerId());
            bidRecord.setBidId(bid.getBidId());
            bidRecord.setBuyerName(bid.getBuyerName());
            bidRecord.setVehicleId(bid.getVehicleId());
            bidRecord.setBidPrice(bid.getBidPrice());
            bidRecord.setDateOfBid(bid.getDateOfBid());
            bidRepository.save(bidRecord);
            return bid;
    }

    public Bid findBidByBuyerId(String buyerId) {
        // if not cached, find the concert
        // return concert
        return bidRepository
                .findById(buyerId)
                .map(bid -> new Bid(bid.getBuyerId(),
                        bid.getBidId(),
                        bid.getBuyerName(),
                        bid.getVehicleId(),
                        bid.getBidPrice(),
                        bid.getDateOfBid()))
                .orElse(null);
    }

    public List<Bid> findAllBidsByBuyerId(String buyerId) {
        List<BidRecord> bidRecords = bidRepository
                .findByBuyerId(buyerId);

        List<Bid> bidsByBuyerId = new ArrayList<>();

        for (BidRecord bidRecord : bidRecords) {
            bidsByBuyerId.add(new Bid(bidRecord.getBuyerId(),
                    bidRecord.getBidId(),
                    bidRecord.getBuyerName(),
                    bidRecord.getVehicleId(),
                    bidRecord.getBidPrice(),
                    bidRecord.getDateOfBid()));
        }

        return bidsByBuyerId;
    }

   public List<Bid> findAllBids(){
       List<Bid> listOfBids = new ArrayList<>();

       Iterable<BidRecord> bidIterator = bidRepository.findAll();
       for(BidRecord record : bidIterator) {
           listOfBids.add(new Bid(record.getBuyerId(),
                   record.getBidId(),
                   record.getBuyerName(),
                   record.getVehicleId(),
                   record.getBidPrice(),
                   record.getDateOfBid()));
       }
       return listOfBids;
    }

}
