package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.service.BidService;
import com.kenzie.appserver.service.model.Bid;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void getById_Exists() throws Exception {
        String id = "test";
        String bidId = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        String vehicleId = UUID.randomUUID().toString();
        double bidPrice = 50.0;
        String dateOfBid = LocalDate.now().toString();

        Bid bid = new Bid(id, bidId, name, vehicleId, bidPrice, dateOfBid);
        Bid bidMade = bidService.makeABid(bid);
        mvc.perform(get("/bid/{buyerId}", bidMade.getBuyerId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("buyerId")
                        .value(is(id)))
                .andExpect(jsonPath("bidId")
                        .value(is(bidId)))
                .andExpect(jsonPath("buyerName")
                        .value(is(name)))
                .andExpect(jsonPath("vehicleId")
                        .value(is(vehicleId)))
                .andExpect(jsonPath("bidPrice")
                        .value(is(bidPrice)))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllBidsById_Exists() throws Exception {
        String id = "test";
        String bidId = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        String vehicleId = UUID.randomUUID().toString();
        double bidPrice = 50.0;
        String dateOfBid = LocalDate.now().toString();

        Bid bid = new Bid(id, bidId, name, vehicleId, bidPrice, dateOfBid);
        Bid bidMade = bidService.makeABid(bid);

        mvc.perform(get("/all/{buyerId}", bidMade.getBuyerId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("buyerId")
                        .value(is(id)))
                .andExpect(jsonPath("bidId")
                        .value(is(bidId)))
                .andExpect(jsonPath("buyerName")
                        .value(is(name)))
                .andExpect(jsonPath("vehicleId")
                        .value(is(vehicleId)))
                .andExpect(jsonPath("bidPrice")
                        .value(is(bidPrice)))
                .andExpect(status().isOk());

    }
}
