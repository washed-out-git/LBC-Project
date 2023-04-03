package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.VehicleRepository;
import com.kenzie.appserver.repositories.model.VehicleRecord;
import com.kenzie.appserver.service.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {this.vehicleRepository = vehicleRepository;}

    public List<Vehicle> findAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        Iterable<VehicleRecord> vehicleRecordIterable = vehicleRepository.findAll();
        for (VehicleRecord record : vehicleRecordIterable) {
            vehicles.add(new Vehicle(record.getVehicleId(),
                    record.getMake(),
                    record.getModel(),
                    record.getYear(),
                    record.getPrice(),
                    record.getSellerId()));
        }

        return vehicles;
    }

    public Vehicle findById(String id) {
        Vehicle exampleFromBackend = vehicleRepository
                .findById(id)
                .map(vehicle -> new Vehicle(vehicle.getVehicleId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(),
                        vehicle.getPrice(), vehicle.getSellerId()))
                .orElse(null);

        return exampleFromBackend;
    }

    public void deleteVehicle(String id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle addNewVehicle(Vehicle vehicle) {
        VehicleRecord vehicleRecord = new VehicleRecord();
        vehicleRecord.setVehicleId(vehicle.getVehicleId());
        vehicleRecord.setMake(vehicle.getMake());
        vehicleRecord.setModel(vehicle.getModel());
        vehicleRecord.setYear(vehicle.getYear());
        vehicleRecord.setPrice(vehicle.getPrice());
        vehicleRecord.setSellerId(vehicle.getSellerId());
        vehicleRepository.save(vehicleRecord);
        return vehicle;
    }

    public void updateVehicle(Vehicle vehicle) {
        if (vehicleRepository.existsById(vehicle.getVehicleId())) {
            VehicleRecord vehicleRecord = new VehicleRecord();
            vehicleRecord.setVehicleId(vehicle.getVehicleId());
            vehicleRecord.setMake(vehicle.getMake());
            vehicleRecord.setModel(vehicle.getModel());
            vehicleRecord.setYear(vehicle.getYear());
            vehicleRecord.setPrice(vehicle.getPrice());
            vehicleRecord.setSellerId(vehicle.getSellerId());
            vehicleRepository.save(vehicleRecord);
        }
    }

}
