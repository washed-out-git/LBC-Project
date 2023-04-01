package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.BuyerRepository;
import com.kenzie.appserver.repositories.VehicleRepository;
import com.kenzie.appserver.repositories.model.BuyerRecord;
import com.kenzie.appserver.repositories.model.VehicleRecord;
import com.kenzie.appserver.service.model.Bid;
import com.kenzie.appserver.service.model.Buyer;
import com.kenzie.appserver.service.model.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class VehicleServiceTest {

    private VehicleRepository vehicleRepository;
    private VehicleService vehicleService;

    @BeforeEach
    void setup() {
        vehicleRepository = mock(VehicleRepository.class);
        vehicleService = new VehicleService(vehicleRepository);
    }

    @Test
    void findVehicleById() {
        // GIVEN
        String id = randomUUID().toString();
        String make = "Ford";
        String model = "Mustang";
        String year = "1969";
        String price = "20000";

        VehicleRecord vehicleRecord = new VehicleRecord();
        vehicleRecord.setVehicleId(id);
        vehicleRecord.setMake(make);
        vehicleRecord.setModel(model);
        vehicleRecord.setYear(year);
        vehicleRecord.setPrice(price);

        // WHEN
        when(vehicleRepository.findById(id)).thenReturn(Optional.of(vehicleRecord));
        Vehicle vehicle = vehicleService.findById(id);

        // THEN
        Assertions.assertNotNull(vehicle, "The object is returned");
        Assertions.assertEquals(vehicleRecord.getVehicleId(), vehicle.getVehicleId(), "The id matches");
        Assertions.assertEquals(vehicleRecord.getMake(), vehicle.getMake(), "The make matches");
        Assertions.assertEquals(vehicleRecord.getModel(), vehicle.getModel(), "The model matches");
        Assertions.assertEquals(vehicleRecord.getYear(), vehicle.getYear(), "The year matches");
        Assertions.assertEquals(vehicleRecord.getPrice(), vehicle.getPrice(), "The price matches");
    }


    @Test
    void addNewVehicle() {
        // GIVEN
        String id = randomUUID().toString();
        String make = "Ford";
        String model = "Mustang";
        String year = "1969";
        String price = "20000";



        Vehicle vehicle = new Vehicle(id, make, model, year, price);

        ArgumentCaptor<VehicleRecord> vehicleRecordCaptor = ArgumentCaptor.forClass(VehicleRecord.class);

        // WHEN
        Vehicle returnedVehicle = vehicleService.addNewVehicle(vehicle);

        // THEN
        Assertions.assertNotNull(returnedVehicle);

        verify(vehicleRepository).save(vehicleRecordCaptor.capture());

        VehicleRecord vehicleRecord = vehicleRecordCaptor.getValue();

        Assertions.assertNotNull(vehicle, "The vehicle record is returned");
        Assertions.assertEquals(vehicleRecord.getVehicleId(), vehicle.getVehicleId(), "The id matches");
        Assertions.assertEquals(vehicleRecord.getMake(), vehicle.getMake(), "The make matches");
        Assertions.assertEquals(vehicleRecord.getModel(), vehicle.getModel(), "The model matches");
        Assertions.assertEquals(vehicleRecord.getYear(), vehicle.getYear(), "The year matches");
        Assertions.assertEquals(vehicleRecord.getPrice(), vehicle.getPrice(), "The price matches");
    }
}
