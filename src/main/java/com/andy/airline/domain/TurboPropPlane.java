package com.andy.airline.domain;

import com.andy.airline.domain.builder.TurboPropPlaneBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("TurboPropPlane")
public class TurboPropPlane extends Aircraft {

    @Column(name="unpaved")
    private boolean ableToLandOnUnpavedRoad;

    @Column(name = "engine_num")
    private int engineNumber;

    public TurboPropPlane(TurboPropPlaneBuilder builder) {
        this.setName(builder.getName());
        this.setFlightRange(builder.getFlightRange());
        this.setTotalCapacity(builder.getTotalCapacity());
        this.setCarryingCapacity(builder.getCarryingCapacity());
        this.setFuelConsumption(builder.getFuelConsumption());
        this.setEngineNumber(builder.getEngineNumber());
        this.setAbleToLandOnUnpavedRoad(builder.isAbleToLandOnUnpavedRoad());
        this.setAirlineCompany(builder.getAirlineCompany());
    }

}
