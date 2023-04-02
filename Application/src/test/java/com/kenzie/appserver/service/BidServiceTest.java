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


}
