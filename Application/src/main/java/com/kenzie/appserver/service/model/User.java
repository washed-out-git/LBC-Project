package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    public final String AVAILABLE = "available";
    public final String SOLD = "sold";
    public final String ALL = "all";

    public List<Vehicle> getVehicles(String status)   {
        //method stub for getting cars based on status
        return new ArrayList<>();
    }
}