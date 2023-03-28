package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Vehicle;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerResponse {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("sellerName")
    private String sellerName;
    @JsonProperty("vehicleList")
    public List<Vehicle> vehicleList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
