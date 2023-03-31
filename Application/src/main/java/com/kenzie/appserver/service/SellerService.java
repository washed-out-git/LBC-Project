package com.kenzie.appserver.service;


import com.kenzie.appserver.repositories.SellerRepository;
import com.kenzie.appserver.repositories.model.SellerRecord;
import com.kenzie.appserver.service.model.Seller;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    private SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }


    public Seller addNewSeller(Seller seller) {
        SellerRecord sellerRecord = new SellerRecord();
        sellerRecord.setUserId(seller.getUserId());
        sellerRecord.setSellerName(seller.getSellerName());
        sellerRepository.save(sellerRecord);
        return seller;
    }

    public Seller findSellerById(String sellerId){
        Seller sellerFromBackend = sellerRepository
                .findById(sellerId)
                .map(seller -> new Seller(seller.getUserId(),
                        seller.getSellerName()))
                .orElse(null);
        return sellerFromBackend;
    }
}
