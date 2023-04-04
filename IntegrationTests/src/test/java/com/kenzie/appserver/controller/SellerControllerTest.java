package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.SellerCreateRequest;
import com.kenzie.appserver.service.SellerService;
import com.kenzie.appserver.service.model.Seller;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class SellerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    SellerService sellerService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getById_Exists() throws Exception {
        String id = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();

        Seller seller = new Seller(id, name);
        Seller persistedExample = sellerService.addNewSeller(seller);
        mvc.perform(get("/createAccount/{sellerId}", persistedExample.getUserId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("userId")
                        .value(is(id)))
                .andExpect(jsonPath("sellerName")
                        .value(is(name)))
                .andExpect(status().isOk());
    }

    @Test
    public void createSeller_CreateSuccessful() throws Exception {
        String sellerId = randomUUID().toString();
        String sellerName = mockNeat.strings().valStr();

        SellerCreateRequest sellerCreateRequest = new SellerCreateRequest();
        sellerCreateRequest.setUserId(sellerId);
        sellerCreateRequest.setSellerName(sellerName);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(post("/createAccount")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(sellerCreateRequest)))
                // THEN
                .andExpect(jsonPath("userId")
                        .exists())
                .andExpect(jsonPath("sellerName")
                        .value(is(sellerName)))
                .andExpect(status().isOk());
    }
}
// credit Teresa Bowen