package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.BidRepository;
import com.kenzie.appserver.repositories.model.BidRecord;
import com.kenzie.appserver.repositories.model.BuyerRecord;
import com.kenzie.appserver.service.model.Bid;
import com.kenzie.appserver.service.model.Buyer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BidServiceTest {

    private BidRepository bidRepository;
    private BidService bidService;

    @BeforeEach
    void setup() {
        bidRepository = mock(BidRepository.class);
        bidService = mock(BidService.class);
    }

    @Test
    void makeABid(){
        String buyerId = randomUUID().toString();
        List<Bid> bidList = new ArrayList<>();

        Buyer buyer = new Buyer(buyerId, "buyername", bidList);
        BuyerRecord buyerRecord = new BuyerRecord();
        buyerRecord.setId(buyerId);
        buyerRecord.setBuyerName("buyername");
        //buyerRecord.setBidList(bidList);

        ArgumentCaptor<BuyerRecord> buyerRecordCaptor = ArgumentCaptor.forClass(BuyerRecord.class);
       // Buyer returnedBuyer = buyerService.addNewBuyer(buyer);

        // WHEN

        List<Bid> newBidList = new ArrayList<>();
        Bid bid = new Bid();
        bid.setBidPrice(100.0);
        bid.setVehicleId(randomUUID().toString());
        newBidList.add(bid);

       //when(buyerRepository.existsById(buyerId)).thenReturn(true);
       // when(buyerService.findBuyerById(buyerId)).thenReturn(buyer);


       // buyerService.makeABid(buyer);
    }


}
