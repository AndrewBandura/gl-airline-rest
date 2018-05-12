package com.andy.airline.domain;

import com.andy.airline.domain.builder.JetPlaneBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("JetPlane")
public class JetPlane extends Aircraft {

    @Column(name = "engine_num")
    private int engineNumber;

    public JetPlane(JetPlaneBuilder builder) {
        this.setName(builder.getName());
        this.setFlightRange(builder.getFlightRange());
        this.setTotalCapacity(builder.getTotalCapacity());
        this.setCarryingCapacity(builder.getCarryingCapacity());
        this.setFuelConsumption(builder.getFuelConsumption());
        this.setEngineNumber(builder.getEngineNumber());
        this.setAirlineCompany(builder.getAirlineCompany());
    }

}
