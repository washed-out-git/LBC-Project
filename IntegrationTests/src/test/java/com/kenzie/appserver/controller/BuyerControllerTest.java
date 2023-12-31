package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.BidCreateRequest;
import com.kenzie.appserver.controller.model.BuyerCreateRequest;
import com.kenzie.appserver.service.BuyerService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void getById_Exists() throws Exception {
        String id = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        List<Bid> bidList = new ArrayList<>();

        Buyer buyer = new Buyer(id, name, bidList);
        Buyer persistedExample = buyerService.addNewBuyer(buyer);
        mvc.perform(get("/buyer/{id}", persistedExample.getUserId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("userId")
                        .value(is(id)))
                .andExpect(jsonPath("buyerName")
                        .value(is(name)))
                .andExpect(status().isOk());
    }

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
                .andExpect(status().isOk());
    }


}
