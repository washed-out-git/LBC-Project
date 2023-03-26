package com.kenzie.appserver.controller;

import com.amazonaws.Response;
import com.kenzie.appserver.controller.model.BidCreateRequest;
import com.kenzie.appserver.controller.model.BidResponse;
import com.kenzie.appserver.controller.model.BuyerCreateRequest;
import com.kenzie.appserver.controller.model.BuyerResponse;
import com.kenzie.appserver.repositories.model.BuyerRecord;
import com.kenzie.appserver.service.BuyerService;
import com.kenzie.appserver.service.model.Bid;
import com.kenzie.appserver.service.model.Buyer;
import com.kenzie.appserver.service.model.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    private BuyerService buyerService;

    BuyerController(BuyerService buyerService){this.buyerService = buyerService;}

    @GetMapping("/{buyerId}")
    public ResponseEntity<BuyerResponse> searchBuyerById(@PathVariable("buyerId") String buyerId) {
        Buyer buyer = buyerService.findBuyerById(buyerId);
        // If there are no concerts, then return a 204
        if (buyer == null) {
            return ResponseEntity.notFound().build();
        }
        // Otherwise, convert it into a ConcertResponses and return it
        BuyerResponse buyerResponse = createBuyerResponse(buyer);
        return ResponseEntity.ok(buyerResponse);
    }

    @PostMapping
    public ResponseEntity<BuyerResponse> addNewBuyer(@RequestBody BuyerCreateRequest buyerCreateRequest) {
        Buyer buyer = new Buyer(buyerCreateRequest.getBuyerName());

        buyerService.addNewBuyer(buyer);

        BuyerResponse buyerResponse = createBuyerResponse(buyer);

        return ResponseEntity.created(URI.create("/buyer/" + buyerResponse.getUserId())).body(buyerResponse);
    }


    @PutMapping
    public ResponseEntity<BuyerResponse> makeABid(@RequestBody BidCreateRequest bidCreateRequest) {

        Bid bid = new Bid();
        bid.setBidPrice(bidCreateRequest.getBidPrice());

        Buyer buyer = new Buyer(bidCreateRequest.getBuyerId(),
                bidCreateRequest.getBuyerName(),
                bidCreateRequest.getBidList());

        buyerService.makeABid(buyer, bid);

        BuyerResponse buyerResponse = createBuyerResponse(buyer);

        return ResponseEntity.ok(buyerResponse);
    }

    private BuyerResponse createBuyerResponse(Buyer buyer) {
        BuyerResponse buyerResponse = new BuyerResponse();
       buyerResponse.setUserId(buyer.getUserId());
       buyerResponse.setBuyerName(buyer.getBuyerName());
       buyerResponse.setBidList(buyer.getBidList());
       return buyerResponse;
    }

}
