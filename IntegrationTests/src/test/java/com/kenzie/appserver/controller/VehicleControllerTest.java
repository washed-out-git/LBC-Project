package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.VehicleCreateRequest;
import com.kenzie.appserver.service.VehicleService;
import com.kenzie.appserver.service.model.Vehicle;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class VehicleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    VehicleService vehicleService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getByMake_Exists() throws Exception {
        String make = "Ford";
        String model = mockNeat.strings().valStr();
        String year = mockNeat.strings().valStr();
        String vehicleId = mockNeat.strings().valStr();
        String price = mockNeat.strings().valStr();

        Vehicle vehicle = new Vehicle(make, model, year, vehicleId, price);
        Vehicle persistedVehicle = vehicleService.addNewVehicle(vehicle);
        mvc.perform(get("/vehicles/{make}", persistedVehicle.getMake())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("make")
                        .value(is(make)))
                .andExpect(jsonPath("model")
                        .value(is(model)))
                .andExpect(jsonPath("year")
                        .value(is(year)))
                .andExpect(jsonPath("vehicleId")
                        .value(is(vehicleId)))
                .andExpect(status().isOk());
    }

    @Test
    public void createVehicle_CreateSuccessful() throws Exception {
        String make = "Ford";
        String model = mockNeat.strings().valStr();
        String year = mockNeat.strings().valStr();
        String vehicleId = mockNeat.strings().valStr();

       VehicleCreateRequest vehicleCreateRequest = new VehicleCreateRequest();
       vehicleCreateRequest.setMake(make);
       vehicleCreateRequest.setModel(model);
       vehicleCreateRequest.setYear(year);
       vehicleCreateRequest.setVehicleId(vehicleId);

        mapper.registerModule(new JavaTimeModule());

        mvc.perform(post("/vehicles")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vehicleCreateRequest)))
                .andExpect(jsonPath("make")
                        .value(is(make)))
                .andExpect(jsonPath("model")
                        .value(is(model)))
                .andExpect(jsonPath("year")
                        .value(is(year)))
                .andExpect(jsonPath("vehicleId")
                        .value(is(vehicleId)))
                .andExpect(status().isCreated());
    }
}
