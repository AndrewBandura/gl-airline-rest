package com.andy.airline.domain.builder;

import com.andy.airline.domain.AirlineCompany;
import com.andy.airline.domain.JetPlane;
import com.andy.airline.domain.TurboPropPlane;
import lombok.Getter;

@Getter
public class TurboPropPlaneBuilder {

    private Long id;
    private String name;
    private int flightRange;
    private int totalCapacity;
    private int carryingCapacity;
    private double fuelConsumption;
    private int engineNumber;
    private boolean ableToLandOnUnpavedRoad;
    private AirlineCompany airlineCompany;


    public TurboPropPlaneBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TurboPropPlaneBuilder setFlightRange(int flightRange) {
        this.flightRange = flightRange;
        return this;
    }

    public TurboPropPlaneBuilder setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
        return this;
    }

    public TurboPropPlaneBuilder setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
        return this;
    }

    public TurboPropPlaneBuilder setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
        return this;
    }

    public TurboPropPlaneBuilder setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
        return this;
    }

    public TurboPropPlaneBuilder setEngineNumber(int engineNumber) {
        this.engineNumber = engineNumber;
        return this;
    }

    public TurboPropPlaneBuilder setAbleToLandOnUnpavedRoad(boolean ableToLandOnUnpavedRoad) {
        this.ableToLandOnUnpavedRoad = ableToLandOnUnpavedRoad;
        return this;
    }

    public TurboPropPlane build(){
        return new TurboPropPlane (this);
    }
}
