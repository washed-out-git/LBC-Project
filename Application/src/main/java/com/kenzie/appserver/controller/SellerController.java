package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.SellerCreateRequest;
import com.kenzie.appserver.controller.model.SellerResponse;
import com.kenzie.appserver.service.SellerService;
import com.kenzie.appserver.service.model.Seller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/createAccount")
public class SellerController {

    private SellerService sellerService;

    SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<SellerResponse> searchSellerById(@PathVariable("sellerId") String userId){
        Seller seller = sellerService.findSellerById(userId);
        if (seller == null) {
            return ResponseEntity.notFound().build();
        }
        SellerResponse sellerResponse = createSellerResponse(seller);
        return ResponseEntity.ok(sellerResponse);
    }

    @PostMapping
    public ResponseEntity<SellerResponse> addNewSeller(@RequestBody SellerCreateRequest sellerCreateRequest) {
        Seller seller = new Seller(sellerCreateRequest.getUserId(), sellerCreateRequest.getSellerName());
        sellerService.addNewSeller(seller);
        SellerResponse sellerResponse = createSellerResponse(seller);
        return ResponseEntity.ok(sellerResponse);

    }

    private SellerResponse createSellerResponse(Seller seller){
        SellerResponse sellerResponse = new SellerResponse();
        sellerResponse.setUserId(seller.getUserId());
        sellerResponse.setSellerName(seller.getSellerName());
        return sellerResponse;
    }
}

// credit Teresa Bowen