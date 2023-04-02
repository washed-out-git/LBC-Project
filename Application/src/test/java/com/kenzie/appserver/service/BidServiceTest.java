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
import static org.mockito.Mockito.*;

public class BidServiceTest {

    private BidRepository bidRepository;
    private BidService bidService;

    @BeforeEach
    void setup() {
        bidRepository = mock(BidRepository.class);
        bidService = new BidService(bidRepository);
    }

    @Test
    void makeABid(){
        String buyerId = "email@email.com";
        String bidId = "bidId";

        Bid bid = new Bid(buyerId,
                bidId,
                "TestName",
                "1234",
                50.0,
                "dateOfBid");

        ArgumentCaptor<BidRecord> bidRecordCaptor = ArgumentCaptor.forClass(BidRecord.class);

        // WHEN
        Bid returnedBid = bidService.makeABid(bid);

        // THEN
        Assertions.assertNotNull(returnedBid);

        verify(bidRepository).save(bidRecordCaptor.capture());

        BidRecord record = bidRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The bid record is returned");
        Assertions.assertEquals(record.getBuyerId(), bid.getBuyerId(), "The bid id matches");
        Assertions.assertEquals(record.getBuyerName(), bid.getBuyerName(), "The bid name matches");
        Assertions.assertEquals(record.getVehicleId(), bid.getVehicleId(), "The vehicle ID matches");
        Assertions.assertEquals(record.getBidPrice(), bid.getBidPrice(), "The bid price matches");
        Assertions.assertEquals(record.getDateOfBid(), bid.getDateOfBid(), "The concert reservation closed flag matches");
    }

    @Test
    void findAllBidsByBuyerId() {
        // GIVEN
        BidRecord record1 = new BidRecord();
        record1.setBidId(randomUUID().toString());
        record1.setBuyerId("buyertoFind");
        record1.setDateOfBid("record1date");
        record1.setBuyerName("bob");
        record1.setVehicleId("1234");
        record1.setBidPrice(50);

        BidRecord record2 = new BidRecord();
        record2.setBidId(randomUUID().toString());
        record2.setBuyerId("buyertoFind");
        record2.setDateOfBid("record1date");
        record2.setBuyerName("bob");
        record2.setVehicleId("1235");
        record2.setBidPrice(50);

        BidRecord record3 = new BidRecord();
        record3.setBidId(randomUUID().toString());
        record3.setBuyerId("RANDOM");
        record3.setDateOfBid("record1date");
        record3.setBuyerName("bob");
        record3.setVehicleId("12354");
        record3.setBidPrice(50);

        List<BidRecord> records = new ArrayList<>();

        records.add(record1);
        records.add(record2);
        records.add(record3);

        when(bidRepository.findAll()).thenReturn(records);
        // WHEN

        List<Bid> bids = bidService.findAllBidsByBuyer(record1.getBuyerId());

        // THEN
        Assertions.assertNotNull(bids, "The reserved ticket list is returned");
        Assertions.assertEquals(2, bids.size(), "There is 3 bids by same user");

        for (Bid bid : bids) {
            if (bid.getBuyerId() == record1.getBuyerId()) {
                Assertions.assertEquals(record1.getBuyerName(), bid.getBuyerName(), "The buyer name matches");
            } else if (bid.getBuyerId() == record2.getBuyerId()) {
                Assertions.assertEquals(record2.getBuyerName(), bid.getBuyerName(), "The buyer name matches");

            } else {
                Assertions.assertTrue(false, "Bids returned that was not in the records!");
            }
        }
    }


}
