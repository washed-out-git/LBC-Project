package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.BuyerRepository;
import com.kenzie.appserver.repositories.model.BuyerRecord;
import com.kenzie.appserver.service.model.Bid;
import com.kenzie.appserver.service.model.Buyer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class BuyerServiceTest {

    private BuyerRepository buyerRepository;
    private BuyerService buyerService;

    @BeforeEach
    void setup() {
        buyerRepository = mock(BuyerRepository.class);
        buyerService = new BuyerService(buyerRepository);
    }

    @Test
    void findBuyerById() {
        // GIVEN
        String id = randomUUID().toString();

        BuyerRecord record = new BuyerRecord();
        record.setId(id);
        record.setBuyerName("BuyerName");
        record.setBidList(new ArrayList<>());

        // WHEN
        when(buyerRepository.findById(id)).thenReturn(Optional.of(record));
        Buyer buyer = buyerService.findBuyerById(id);

        // THEN
        Assertions.assertNotNull(buyer, "The object is returned");
        Assertions.assertEquals(record.getId(), buyer.getUserId(), "The id matches");
        Assertions.assertEquals(record.getBuyerName(), buyer.getBuyerName(), "The name matches");
        Assertions.assertEquals(record.getBidList(), buyer.getBidList(), "The list matches");
    }


    @Test
    void addNewBuyer() {
        // GIVEN
        String buyerId = randomUUID().toString();
        List<Bid> bidList = new ArrayList<>();

        Buyer buyer = new Buyer(buyerId, "BuyerName", bidList);

        ArgumentCaptor<BuyerRecord> buyerRecordCaptor = ArgumentCaptor.forClass(BuyerRecord.class);

        // WHEN
        Buyer returnedBuyer = buyerService.addNewBuyer(buyer);

        // THEN
        Assertions.assertNotNull(returnedBuyer);

        verify(buyerRepository).save(buyerRecordCaptor.capture());

        BuyerRecord record = buyerRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The buyer record is returned");
        Assertions.assertEquals(record.getId(), buyer.getUserId(), "The user id matches");
        Assertions.assertEquals(record.getBuyerName(), buyer.getBuyerName(), "The buyer name matches");
        Assertions.assertEquals(record.getBidList(), buyer.getBidList(), "The bid list matches");
    }

    @Test
    void makeABid(){
        String buyerId = randomUUID().toString();
        List<Bid> bidList = new ArrayList<>();

        Buyer buyer = new Buyer(buyerId, "buyername", bidList);

        ArgumentCaptor<BuyerRecord> buyerRecordCaptor = ArgumentCaptor.forClass(BuyerRecord.class);
        Buyer returnedBuyer = buyerService.addNewBuyer(buyer);

        // WHEN

        List<Bid> newBidList = new ArrayList<>();
        Bid bid = new Bid();
        bid.setBidPrice(100.0);
        bid.setVehicleId(randomUUID().toString());
        newBidList.add(bid);

        when(buyerRepository.existsById(buyerId)).thenReturn(true);

        buyerService.makeABid(buyer, bid);

        verify(buyerRepository).save(buyerRecordCaptor.capture());
    }

    @Test
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

}
