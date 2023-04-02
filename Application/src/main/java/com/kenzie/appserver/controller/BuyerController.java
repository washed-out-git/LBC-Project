package com.kenzie.appserver.controller;
import com.kenzie.appserver.controller.model.BuyerCreateRequest;
import com.kenzie.appserver.controller.model.BuyerResponse;
import com.kenzie.appserver.controller.model.SellerResponse;
import com.kenzie.appserver.service.BuyerService;
import com.kenzie.appserver.service.model.Buyer;
import com.kenzie.appserver.service.model.Seller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/buyer")
public class BuyerController {

    private BuyerService buyerService;

    BuyerController(BuyerService buyerService){this.buyerService = buyerService;}

    @PostMapping
    public ResponseEntity<BuyerResponse> addNewBuyer(@RequestBody BuyerCreateRequest buyerCreateRequest) {
        Buyer buyer = new Buyer(buyerCreateRequest.getUserId(),buyerCreateRequest.getBuyerName());

        BuyerResponse buyerResponse = createBuyerResponse(buyerService.addNewBuyer(buyer));

        return ResponseEntity.ok(buyerResponse);
    }

    @GetMapping("/{buyerId}")
    public ResponseEntity<BuyerResponse> searchBuyerById(@PathVariable("buyerId") String userId){
        Buyer buyer = buyerService.findBuyerById(userId);
        if (buyer == null) {
            return ResponseEntity.notFound().build();
        }
        BuyerResponse buyerResponse = createBuyerResponse(buyer);
        return ResponseEntity.ok(buyerResponse);
    }

    private BuyerResponse createBuyerResponse(Buyer buyer) {
        BuyerResponse buyerResponse = new BuyerResponse();
       buyerResponse.setUserId(buyer.getUserId());
       buyerResponse.setBuyerName(buyer.getBuyerName());
       //buyerResponse.setBidList(buyer.getBidList());
       return buyerResponse;
    }




}
