package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.BuyerRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyerService {
    private BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository){
        this.buyerRepository = buyerRepository;
    }


    public void makeABid(){

    }

    public List<Vehicle> getListofCars(){
        return new ArrayList<>();
    }


    public List<String> getListofBids(){
        return new ArrayList<>();
    }

}
