package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.model.BuyerRecord;
import com.kenzie.appserver.service.model.Bid;
import com.kenzie.appserver.service.model.Buyer;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.when;

public class BidServiceTest {

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

   /* @Test
    void findAllBids_two_bids() {
        // GIVEN

        String buyerId = randomUUID().toString();
        List<Bid> bidList = new ArrayList<>();

        Buyer buyer = new Buyer(buyerId, "BuyerName", bidList);

        ArgumentCaptor<BuyerRecord> buyerRecordCaptor = ArgumentCaptor.forClass(BuyerRecord.class);

        buyerService.addNewBuyer(buyer);

        Bid bid = new Bid();
        bid.setBidPrice(100.0);
        bid.setVehicleId(randomUUID().toString());
        bidList.add(bid);

        Bid bid2 = new Bid();
        bid2.setBidPrice(110.0);
        bid2.setVehicleId(randomUUID().toString());
        bidList.add(bid2);

        when(buyerRepository.existsById(buyerId)).thenReturn(true);

        buyerService.makeABid(buyer, bid);

        // WHEN
        when(buyerService.findAllBids(buyerId)).thenReturn(bidList);

        // THEN
        verify(buyerRepository).save(buyerRecordCaptor.capture());
        Assertions.assertNotNull(bidList, "The bid list is returned");
        Assertions.assertEquals(2, bidList.size(), "There are two bids");
        }
*/
}
