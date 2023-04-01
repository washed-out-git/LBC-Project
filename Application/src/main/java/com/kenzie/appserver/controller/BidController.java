package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.BidCreateRequest;
import com.kenzie.appserver.controller.model.BidResponse;
import com.kenzie.appserver.service.BidService;
import com.kenzie.appserver.service.BuyerService;
import com.kenzie.appserver.service.model.Bid;
import com.kenzie.appserver.service.model.Buyer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bid")
public class BidController {

    private BidService bidService;

    BidController(BidService bidService){this.bidService = bidService;}

    @PostMapping
    public ResponseEntity<BidResponse> makeABid(@RequestBody BidCreateRequest bidCreateRequest) {

        Bid bid = new Bid();
        bid.setBuyerId(bidCreateRequest.getBuyerId());
        bid.setBuyerName(bidCreateRequest.getBuyerName());
        bid.setVehicleId(bidCreateRequest.getVehicleId());
        bid.setBidPrice(bidCreateRequest.getBidPrice());
        bidService.makeABid(bid);
        BidResponse bidResponse = createBidResponse(bid);
        return ResponseEntity.ok(bidResponse);
    }

    @GetMapping("/{buyerId}")
    public ResponseEntity<BidResponse> searchBidById(@PathVariable("buyerId") String buyerId) {
        Bid bid = bidService.findBidByBuyerId(buyerId);
        // If there are no concerts, then return a 204
        if (bid == null) {
            return ResponseEntity.notFound().build();
        }
        // Otherwise, convert it into a ConcertResponses and return it
        BidResponse bidResponse = createBidResponse(bid);
        return ResponseEntity.ok(bidResponse);
    }


   @GetMapping("/listOfBids")
   public ResponseEntity<List<Bid>> getAllBidsByBuyer() {
       List<Bid> bids = bidService.findAllBids();
        //If there are no bids, then return a 204
        if (bids == null ||  bids.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bids);
    }

    private BidResponse createBidResponse(Bid bid) {
        BidResponse bidResponse = new BidResponse();
        bidResponse.setBuyerId(bid.getBuyerId());
        bidResponse.setBuyerName(bid.getBuyerName());
        bidResponse.setVehicleId(bid.getVehicleId());
        bidResponse.setBidPrice(bid.getBidPrice());
        return bidResponse;
    }


}
