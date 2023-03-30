//package com.kenzie.appserver.service;
//
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.kenzie.appserver.repositories.ExampleRepository;
//import com.kenzie.appserver.repositories.SellerRepository;
//import com.kenzie.appserver.repositories.model.SellerRecord;
//import com.kenzie.appserver.service.model.Seller;
//import org.junit.jupiter.api.Test;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class SellerUnitTest {
//    public SellerRecord sellerRecord;
//    public SellerRepository sellerRepository = mock(SellerRepository.class);
//    public SellerService sellerService = new SellerService(sellerRepository);
//
//
//    @Test
//    void sellerRecordTest(){
//        Seller test = new Seller("testEmail", "Name");
//
//        sellerService.addNewSeller(test);
//
//        Seller test2 =  sellerService.findSellerById("testId");
//
//
//
//    }
//
//
//}
