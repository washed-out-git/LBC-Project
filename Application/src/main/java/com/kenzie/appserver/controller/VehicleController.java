package com.kenzie.appserver.controller;

import com.amazonaws.services.dynamodbv2.xspec.L;
import com.kenzie.appserver.controller.model.*;
import com.kenzie.appserver.service.VehicleService;
import com.kenzie.appserver.service.model.Vehicle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;


@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    VehicleController(VehicleService vehicleService) {this.vehicleService = vehicleService;}

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getById(@PathVariable("id") String id) {

        Vehicle vehicle = vehicleService.findById(id);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }

        VehicleResponse vehicleResponse = createVehicleResponse(vehicle);
        return ResponseEntity.ok(vehicleResponse);
    }
    @GetMapping("/all")
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.findAllVehicles();

        if (vehicles == null || vehicles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<VehicleResponse> responses = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            responses.add(this.createVehicleResponse(vehicle));
        }

        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> addNewVehicle(@RequestBody VehicleCreateRequest vehicleCreateRequest) {
        Vehicle vehicle = new Vehicle(randomUUID().toString(),
                vehicleCreateRequest.getMake(),
                vehicleCreateRequest.getModel(),
                vehicleCreateRequest.getYear(),
                vehicleCreateRequest.getPrice());
        vehicleService.addNewVehicle(vehicle);

        VehicleResponse vehicleResponse = createVehicleResponse(vehicle);

        return ResponseEntity.created(URI.create("/vehicle/" + vehicleResponse.getVehicleId())).body(vehicleResponse);
    }

    @PutMapping
    public ResponseEntity<VehicleResponse> updateVehicle(@RequestBody VehicleUpdateRequest vehicleUpdateRequest) {
        Vehicle vehicle = new Vehicle(vehicleUpdateRequest.getVehicleId(),
                vehicleUpdateRequest.getMake(),
                vehicleUpdateRequest.getModel(),
                vehicleUpdateRequest.getYear(),
                vehicleUpdateRequest.getPrice());
        vehicleService.updateVehicle(vehicle);

        VehicleResponse vehicleResponse = createVehicleResponse(vehicle);

        return ResponseEntity.ok(vehicleResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteConcertById(@PathVariable("id") String id) {
        // Your code here
        vehicleService.deleteVehicle(id);
        return ResponseEntity.status(204).build();
    }

    private  VehicleResponse createVehicleResponse(Vehicle vehicle) {
        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setVehicleId(vehicle.getVehicleId());
        vehicleResponse.setMake(vehicle.getMake());
        vehicleResponse.setModel(vehicle.getModel());
        vehicleResponse.setYear(vehicle.getYear());
        vehicleResponse.setPrice(vehicle.getPrice());
        return vehicleResponse;
    }
}
