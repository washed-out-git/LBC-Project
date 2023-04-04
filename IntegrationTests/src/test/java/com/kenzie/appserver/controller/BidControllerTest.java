package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.BidCreateRequest;
import com.kenzie.appserver.controller.model.BuyerCreateRequest;
import com.kenzie.appserver.service.BidService;
import com.kenzie.appserver.service.model.Bid;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class BidControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    BidService bidService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getByAllBids_Exists() throws Exception {
        String id = UUID.randomUUID().toString();
        String bidId = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        String vehicleId = UUID.randomUUID().toString();
        double bidPrice = 50.0;
        String dateOfBid = LocalDate.now().toString();

        String id2 = UUID.randomUUID().toString();
        String bidId2 = UUID.randomUUID().toString();
        String name2 = mockNeat.strings().valStr();
        String vehicleId2 = UUID.randomUUID().toString();
        double bidPrice2 = 50.0;
        String dateOfBid2 = LocalDate.now().toString();

        Bid bid = new Bid(id, bidId, name, vehicleId, bidPrice, dateOfBid);
        Bid bid2 = new Bid(id2, bidId2, name2, vehicleId2, bidPrice2, dateOfBid2);

        Bid bidMade = bidService.makeABid(bid);
        Bid bidMade2 = bidService.makeABid(bid2);

        mvc.perform(get("/bid/listOfBids")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void createBid_CreateSuccessful() throws Exception {
        String buyerId = randomUUID().toString();
        String buyerName = mockNeat.strings().valStr();
        String vehicleId = mockNeat.strings().valStr();
        Double bidPrice = 50.0;

        BidCreateRequest bidCreateRequest = new BidCreateRequest();
        bidCreateRequest.setBuyerId(buyerId);
        bidCreateRequest.setBuyerName(buyerName);
        bidCreateRequest.setVehicleId(vehicleId);
        bidCreateRequest.setBidPrice(50.0);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(post("/bid")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bidCreateRequest)))
                // THEN
                .andExpect(jsonPath("buyerId")
                        .exists())
                .andExpect(jsonPath("vehicleId")
                        .value(is(vehicleId)))
                .andExpect(jsonPath("bidPrice")
                        .value(is(bidPrice)))
                .andExpect(status().isOk());
    }

}
