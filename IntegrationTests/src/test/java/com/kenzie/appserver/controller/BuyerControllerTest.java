package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.BidCreateRequest;
import com.kenzie.appserver.controller.model.BuyerCreateRequest;
import com.kenzie.appserver.controller.model.ExampleCreateRequest;
import com.kenzie.appserver.service.BuyerService;
import com.kenzie.appserver.service.ExampleService;
import com.kenzie.appserver.service.model.Bid;
import com.kenzie.appserver.service.model.Buyer;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class BuyerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    BuyerService buyerService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createBuyer_CreateSuccessful() throws Exception {
        String buyerId = randomUUID().toString();
        String buyerName = mockNeat.strings().valStr();
        List<Bid> bidList = new ArrayList<>();

        BuyerCreateRequest buyerCreateRequest = new BuyerCreateRequest();
        buyerCreateRequest.setUserId(buyerId);
        buyerCreateRequest.setBuyerName(buyerName);
        buyerCreateRequest.setBidList(bidList);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(post("/buyer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(buyerCreateRequest)))
                // THEN
                .andExpect(jsonPath("userId")
                        .exists())
                .andExpect(jsonPath("buyerName")
                        .value(is(buyerName)))
                .andExpect(jsonPath("bidList")
                        .value(is(bidList)))
                .andExpect(status().isCreated());
    }

    @Test
    public void makeABid_PutSuccessful() throws Exception {
        // GIVEN
        String buyerId = randomUUID().toString();
        String buyerName = mockNeat.strings().valStr();
        List<Bid> bidList = new ArrayList<>();

        Buyer buyer = new Buyer(buyerName);
        Buyer persistedBuyer = buyerService.addNewBuyer(buyer);

        Bid bid = new Bid();
        bid.setBidPrice(100.0);

        bidList.add(bid);

        BuyerCreateRequest buyerCreateRequest = new BuyerCreateRequest();
        buyerCreateRequest.setUserId(buyerId);
        buyerCreateRequest.setBuyerName(buyerName);
        buyerCreateRequest.setBidList(bidList);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(put("/buyer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(buyerCreateRequest)))
                // THEN
                .andExpect(jsonPath("userId")
                        .exists())
                .andExpect(jsonPath("buyerName")
                        .value(is(buyerName)))
                .andExpect(jsonPath("bidList")
                        .value(is(bidList)))
                .andExpect(status().isOk());
    }


}
