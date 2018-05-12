package com.andy.airline.domain.builder;

import com.andy.airline.domain.AirlineCompany;
import com.andy.airline.domain.Helicopter;
import com.andy.airline.domain.JetPlane;
import lombok.Getter;

@Getter
public class HelicopterBuilder {

    private Long id;
    private String name;
    private int flightRange;
    private int totalCapacity;
    private int carryingCapacity;
    private double fuelConsumption;
    private AirlineCompany airlineCompany;
    private String registryNumber;

    public HelicopterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HelicopterBuilder setFlightRange(int flightRange) {
        this.flightRange = flightRange;
        return this;
    }

    public HelicopterBuilder setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
        return this;
    }

    public HelicopterBuilder setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
        return this;
    }

    public HelicopterBuilder setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
        return this;
    }

    public HelicopterBuilder setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
        return this;
    }

    public HelicopterBuilder setRegistryNumber(String helicopterRegistryNumber) {
        registryNumber = helicopterRegistryNumber;
        return this;
    }

    public Helicopter build(){ return new Helicopter(this);
    }
}
