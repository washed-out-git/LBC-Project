package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.SellerRepository;
import com.kenzie.appserver.repositories.model.BuyerRecord;
import com.kenzie.appserver.repositories.model.SellerRecord;
import com.kenzie.appserver.service.model.Bid;
import com.kenzie.appserver.service.model.Buyer;
import com.kenzie.appserver.service.model.Seller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class SellerServiceTest {

    private SellerRepository sellerRepository;
    private SellerService sellerService;

    @BeforeEach
    void setup(){
        sellerRepository = mock(SellerRepository.class);
        sellerService = new SellerService(sellerRepository);
    }

    @Test
    void findBuyerById() {
        // GIVEN
        String id = randomUUID().toString();

        SellerRecord record = new SellerRecord();
        record.setUserId(id);
        record.setSellerName("SellerName");

        // WHEN
        when(sellerRepository.findById(id)).thenReturn(Optional.of(record));
        Seller seller = sellerService.findSellerById(id);

        // THEN
        Assertions.assertNotNull(seller, "The object is returned");
        Assertions.assertEquals(record.getUserId(), seller.getUserId(), "The id matches");
        Assertions.assertEquals(record.getSellerName(), seller.getSellerName(), "The name matches");
    }


    @Test
    void addNewBuyer() {
        // GIVEN
        String buyerId = randomUUID().toString();

        Seller seller = new Seller(buyerId, "SellerName");

        ArgumentCaptor<SellerRecord> sellerRecordCaptor = ArgumentCaptor.forClass(SellerRecord.class);

        // WHEN
        Seller returnedSeller = sellerService.addNewSeller(seller);

        // THEN
        Assertions.assertNotNull(returnedSeller);

        verify(sellerRepository).save(sellerRecordCaptor.capture());

        SellerRecord record = sellerRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The seller record is returned");
        Assertions.assertEquals(record.getUserId(), seller.getUserId(), "The user id matches");
        Assertions.assertEquals(record.getSellerName(), seller.getSellerName(), "The buyer name matches");
    }

}