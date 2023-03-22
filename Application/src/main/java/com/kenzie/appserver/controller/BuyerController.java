package com.kenzie.appserver.controller;

import com.amazonaws.Response;
import com.kenzie.appserver.controller.model.BidCreateRequest;
import com.kenzie.appserver.controller.model.BidResponse;
import com.kenzie.appserver.repositories.model.BuyerRecord;
import com.kenzie.appserver.service.BuyerService;
import com.kenzie.appserver.service.model.Bid;
import com.kenzie.appserver.service.model.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Buyer")
public class BuyerController {

    private BuyerService buyerService;

    BuyerController(BuyerService buyerService){this.buyerService = buyerService;}

    @PostMapping
    public ResponseEntity<BidResponse> makeABid(@RequestBody BidCreateRequest bidCreateRequest){

        buyerService.makeABid(bidCreateRequest.getBuyerId(), bidCreateRequest.getVehicleId(), bidCreateRequest.getBidPrice());

        return new ResponseEntity(HttpStatus.OK);

    }

}
