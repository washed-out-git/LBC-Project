package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.VehicleRepository;
import com.kenzie.appserver.repositories.model.ExampleRecord;
import com.kenzie.appserver.repositories.model.VehicleRecord;
import com.kenzie.appserver.service.model.Example;
import com.kenzie.appserver.service.model.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {this.vehicleRepository = vehicleRepository;}

    public Vehicle findByMake(String make) {
        Vehicle exampleFromBackend =vehicleRepository
                .findById(make)
                .map(vehicle -> new Vehicle(vehicle.getMake(), vehicle.getModel(), vehicle.getYear(),
                        vehicle.isAvailable(), vehicle.getVehicleId()))
                .orElse(null);

        return exampleFromBackend;
    }


    public Vehicle addNewVehicle(Vehicle vehicle) {
        VehicleRecord vehicleRecord = new VehicleRecord();
        vehicleRecord.setMake(vehicle.getMake());
        vehicleRecord.setModel(vehicle.getModel());
        vehicleRecord.setYear(vehicle.getYear());
        vehicleRecord.setAvailable(vehicle.isAvailable());
        vehicleRecord.setVehicleId(vehicle.getVehicleId());
        return vehicle;
    }

}
