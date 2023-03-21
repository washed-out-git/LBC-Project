package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ExampleCreateRequest;
import com.kenzie.appserver.controller.model.ExampleResponse;
import com.kenzie.appserver.controller.model.VehicleCreateRequest;
import com.kenzie.appserver.controller.model.VehicleResponse;
import com.kenzie.appserver.service.VehicleService;
import com.kenzie.appserver.service.model.Example;
import com.kenzie.appserver.service.model.Vehicle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    VehicleController(VehicleService vehicleService) {this.vehicleService = vehicleService;}

    @GetMapping("/{make}")
    public ResponseEntity<VehicleResponse> get(@PathVariable("make") String make) {

        Vehicle vehicle = vehicleService.findByMake(make);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }

        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setMake(vehicle.getMake());
        vehicleResponse.setModel(vehicle.getModel());
        vehicleResponse.setYear(vehicle.getYear());
        vehicleResponse.setAvailable(vehicle.isAvailable());
        return ResponseEntity.ok(vehicleResponse);
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> addNewVehicle(@RequestBody VehicleCreateRequest vehicleCreateRequest) {
        Vehicle vehicle = new Vehicle(vehicleCreateRequest.getMake(), vehicleCreateRequest.getModel(),
                vehicleCreateRequest.getYear(), vehicleCreateRequest.isAvailable());
        vehicleService.addNewVehicle(vehicle);

        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setMake(vehicle.getMake());
        vehicleResponse.setModel(vehicle.getModel());
        vehicleResponse.setYear(vehicle.getYear());
        vehicleResponse.setAvailable(vehicle.isAvailable());

        return ResponseEntity.created(URI.create("/example/" + vehicleResponse.getMake())).body(vehicleResponse);
    }
}
