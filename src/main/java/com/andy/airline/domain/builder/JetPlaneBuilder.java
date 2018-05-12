package com.andy.airline.domain.builder;

import com.andy.airline.domain.AirlineCompany;
import com.andy.airline.domain.JetPlane;
import lombok.Getter;

@Getter
public class JetPlaneBuilder {

    private Long id;
    private String name;
    private int flightRange;
    private int totalCapacity;
    private int carryingCapacity;
    private double fuelConsumption;
    private int engineNumber;
    private AirlineCompany airlineCompany;


    public JetPlaneBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public JetPlaneBuilder setFlightRange(int flightRange) {
        this.flightRange = flightRange;
        return this;
    }

    public JetPlaneBuilder setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
        return this;
    }

    public JetPlaneBuilder setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
        return this;
    }

    public JetPlaneBuilder setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
        return this;
    }

    public JetPlaneBuilder setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
        return this;
    }

    public JetPlaneBuilder setEngineNumber(int engineNumber) {
        this.engineNumber = engineNumber;
        return this;
    }

    public JetPlane build(){
        return new JetPlane (this);
    }
}
