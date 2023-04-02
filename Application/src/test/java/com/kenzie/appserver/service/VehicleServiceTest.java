package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.VehicleRepository;
import com.kenzie.appserver.repositories.model.VehicleRecord;
import com.kenzie.appserver.service.model.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        String sellerId= randomUUID().toString();

        VehicleRecord vehicleRecord = new VehicleRecord();
        vehicleRecord.setVehicleId(id);
        vehicleRecord.setMake(make);
        vehicleRecord.setModel(model);
        vehicleRecord.setYear(year);
        vehicleRecord.setPrice(price);
        vehicleRecord.setSellerId(sellerId);

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
        Assertions.assertEquals(vehicleRecord.getSellerId(), vehicle.getSellerId(), "The sellerId matches");
    }


    @Test
    void addNewVehicle() {
        // GIVEN
        String id = randomUUID().toString();
        String make = "Ford";
        String model = "Mustang";
        String year = "1969";
        String price = "20000";
        String sellerId = randomUUID().toString();


        Vehicle vehicle = new Vehicle(id, make, model, year, price, sellerId);

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
        Assertions.assertEquals(vehicleRecord.getSellerId(), vehicle.getSellerId(), "The sellerId matches");
    }

    @Test
    void getAllVehicles() {
        String id = randomUUID().toString();
        String make =  randomUUID().toString();
        String model =  randomUUID().toString();
        String year =  randomUUID().toString();
        String price =  randomUUID().toString();
        String sellerId = randomUUID().toString();
        String id1 = randomUUID().toString();
        String make1 = "Ford";
        String model1 = "Mustang";
        String year1 = "1969";
        String price1 = "20000";
        String sellerId1 = randomUUID().toString();

        VehicleRecord vehicleRecord = new VehicleRecord();
        vehicleRecord.setVehicleId(id);
        vehicleRecord.setMake(make);
        vehicleRecord.setModel(model);
        vehicleRecord.setYear(year);
        vehicleRecord.setPrice(price);
        vehicleRecord.setSellerId(sellerId);
        VehicleRecord vehicleRecord1 = new VehicleRecord();
        vehicleRecord1.setVehicleId(id1);
        vehicleRecord1.setMake(make1);
        vehicleRecord1.setModel(model1);
        vehicleRecord1.setYear(year1);
        vehicleRecord1.setPrice(price1);
        vehicleRecord1.setSellerId(sellerId1);

        List<VehicleRecord> recordList = new ArrayList<>();
        recordList.add(vehicleRecord);
        recordList.add(vehicleRecord1);
        when(vehicleRepository.findAll()).thenReturn(recordList);

        // WHEN
        List<Vehicle> vehicles = vehicleService.findAllVehicles();

        // THEN
        assertNotNull(vehicles, "The vehicle list is returned");
        assertEquals(2, vehicles.size(), "There are two vehicles");

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId() == vehicleRecord.getVehicleId()) {
                assertEquals(vehicleRecord.getVehicleId(), vehicle.getVehicleId(), "The vehicle id matches");
                assertEquals(vehicleRecord.getMake(), vehicle.getMake(), "The vehicle make matches");
                assertEquals(vehicleRecord.getModel(), vehicle.getModel(), "The vehicle model matches");
                assertEquals(vehicleRecord.getYear(), vehicle.getYear(), "The vehicle year matches");
                assertEquals(vehicleRecord.getPrice(), vehicle.getPrice(), "The vehicle price matches");
                assertEquals(vehicleRecord.getSellerId(), vehicle.getSellerId(), "The vehicle sellerId matches");
            } else if (vehicle.getVehicleId() == vehicleRecord1.getVehicleId()) {
                assertEquals(vehicleRecord1.getVehicleId(), vehicle.getVehicleId(), "The vehicle id matches");
                assertEquals(vehicleRecord1.getMake(), vehicle.getMake(), "The vehicle make matches");
                assertEquals(vehicleRecord1.getModel(), vehicle.getModel(), "The vehicle model matches");
                assertEquals(vehicleRecord1.getYear(), vehicle.getYear(), "The vehicle year matches");
                assertEquals(vehicleRecord1.getPrice(), vehicle.getPrice(), "The vehicle price matches");
                assertEquals(vehicleRecord1.getSellerId(), vehicle.getSellerId(), "The vehicle sellerId matches");
            } else {
                Assertions.assertTrue(false, "Vehicle returned that was not in the records!");
            }
        }
    }

    @Test
    void updateVehicle() {
        //GIVEN
        String id = randomUUID().toString();
        String make = "Ford";
        String model = "Mustang";
        String year = "1969";
        String price = "20000";
        String sellerId = randomUUID().toString();

        Vehicle vehicle = new Vehicle(id, make, model, year, price, sellerId);

        VehicleRecord record = new VehicleRecord();
        record.setVehicleId(vehicle.getVehicleId());
        record.setMake(vehicle.getMake());
        record.setModel(vehicle.getModel());
        record.setYear(vehicle.getYear());
        record.setPrice(vehicle.getPrice());
        record.setSellerId(sellerId);

        //WHEN
        when(vehicleRepository.existsById(id)).thenReturn(true);
        vehicleService.updateVehicle(vehicle);

        //THEN
        verify(vehicleRepository).save(record);
        verify(vehicleRepository).existsById(id);

        assertNotNull(vehicle, "The vehicle is returned");
        assertEquals(record.getVehicleId(), vehicle.getVehicleId(), "The vehicle id matches");
        assertEquals(record.getMake(), vehicle.getMake(), "The vehicle make matches");
        assertEquals(record.getModel(), vehicle.getModel(), "The vehicle model matches");
        assertEquals(record.getYear(), vehicle.getYear(), "The vehicle year matches");
        assertEquals(record.getPrice(), vehicle.getPrice(), "The vehicle price matches");
        assertEquals(record.getSellerId(), vehicle.getSellerId(), "The vehicle sellerId matches");
    }

    @Test
    void deleteVehicle() {
        //GIVEN
        String id = randomUUID().toString();
        String make = "Ford";
        String model = "Mustang";
        String year = "1969";
        String price = "20000";
        String sellerId = randomUUID().toString();

        VehicleRecord record = new VehicleRecord();
        record.setVehicleId(id);
        record.setMake(make);
        record.setModel(model);
        record.setYear(year);
        record.setPrice(price);
        record.setSellerId(sellerId);

        Vehicle vehicle = new Vehicle(record.getVehicleId(),
                record.getMake(),
                record.getModel(),
                record.getYear(),
                record.getPrice(),
                record.getSellerId());

        //WHEN
        ArgumentCaptor<VehicleRecord> captor = ArgumentCaptor.forClass(VehicleRecord.class);

        //THEN
        vehicleService.deleteVehicle(vehicle.getVehicleId());
        verify(vehicleRepository).deleteById(vehicle.getVehicleId());

    }
}
