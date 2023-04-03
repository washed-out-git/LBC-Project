package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.VehicleCreateRequest;
import com.kenzie.appserver.controller.model.VehicleUpdateRequest;
import com.kenzie.appserver.service.VehicleService;
import com.kenzie.appserver.service.model.Vehicle;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void getById_Exists() throws Exception {
        String vehicleId = "1234";
        String make = mockNeat.strings().valStr();
        String model = mockNeat.strings().valStr();
        String year = mockNeat.strings().valStr();
        String price = mockNeat.strings().valStr();

        Vehicle vehicle = new Vehicle(vehicleId, make, model, year, price);

        Vehicle persistedVehicle = vehicleService.addNewVehicle(vehicle);
        mvc.perform(get("/vehicle/{id}", persistedVehicle.getVehicleId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id")
                        .value(is(vehicleId)))
                .andExpect(jsonPath("make")
                        .value(is(make)))
                .andExpect(jsonPath("model")
                        .value(is(model)))
                .andExpect(jsonPath("year")
                        .value(is(year)))
                .andExpect(jsonPath("price")
                        .value(is(price)))
                .andExpect(status().isOk());
    }

    @Test
    public void createVehicle_CreateSuccessful() throws Exception {
        String make = mockNeat.strings().valStr();
        String model = mockNeat.strings().valStr();
        String year = mockNeat.strings().valStr();
        String vehicleId = "1234";
        String price = mockNeat.strings().valStr();

       VehicleCreateRequest vehicleCreateRequest = new VehicleCreateRequest();
       vehicleCreateRequest.setMake(make);
       vehicleCreateRequest.setModel(model);
       vehicleCreateRequest.setYear(year);
       vehicleCreateRequest.setVehicleId(vehicleId);
       vehicleCreateRequest.setPrice(price);

        mapper.registerModule(new JavaTimeModule());

        mvc.perform(post("/vehicle")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vehicleCreateRequest)))
                .andExpect(jsonPath("id")
                        .value(is(vehicleId)))
                .andExpect(jsonPath("make")
                        .value(is(make)))
                .andExpect(jsonPath("model")
                        .value(is(model)))
                .andExpect(jsonPath("year")
                        .value(is(year)))
                .andExpect(jsonPath("price")
                        .value(is(price)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getVehicle_VehicleDoesNotExist() throws Exception {
        // GIVEN
        String id = UUID.randomUUID().toString();
        // WHEN
        mvc.perform(get("/vehicle/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateVehicle_PutSuccessful() throws Exception {
        // GIVEN
        String id = "1234";
        String make = mockNeat.strings().valStr();
        String model = mockNeat.strings().valStr();
        String year = mockNeat.strings().valStr();
        String price = mockNeat.strings().valStr();

        Vehicle vehicle = new Vehicle(id, make, model, year, price);
        Vehicle persistedVehicle = vehicleService.addNewVehicle(vehicle);

        String newMake = mockNeat.strings().valStr();
        String newModel = mockNeat.strings().valStr();
        String newYear = mockNeat.strings().valStr();
        String newPrice = mockNeat.strings().valStr();

        VehicleUpdateRequest vehicleUpdateRequest = new VehicleUpdateRequest();
        vehicleUpdateRequest.setVehicleId(id);
        vehicleUpdateRequest.setMake(newMake);
        vehicleUpdateRequest.setModel(newModel);
        vehicleUpdateRequest.setYear(newYear);
        vehicleUpdateRequest.setPrice(newPrice);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(put("/vehicle")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(vehicleUpdateRequest)))
                // THEN
                .andExpect(jsonPath("id")
                        .exists())
                .andExpect(jsonPath("make")
                        .value(is(newMake)))
                .andExpect(jsonPath("model")
                        .value(is(newModel)))
                .andExpect(jsonPath("year")
                        .value(is(newYear)))
                .andExpect(jsonPath("price")
                        .value(is(newPrice)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteConcert_DeleteSuccessful() throws Exception {
        // GIVEN
        String id = "1234";
        String make = mockNeat.strings().valStr();
        String model = mockNeat.strings().valStr();
        String year = mockNeat.strings().valStr();
        String price = mockNeat.strings().valStr();

        Vehicle vehicle = new Vehicle(id, make, model, year, price);
        Vehicle persistedVehicle = vehicleService.addNewVehicle(vehicle);

        // WHEN
        mvc.perform(delete("/vehicle/{id}", persistedVehicle.getVehicleId())
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNoContent());
        assertThat(vehicleService.findById(id)).isNull();
    }
}
