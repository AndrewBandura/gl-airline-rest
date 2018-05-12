package com.andy.airline.domain;

import com.andy.airline.domain.builder.HelicopterBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("Helicopter")
public class Helicopter extends Aircraft {

    @Column(name = "reg_num")
    private @Setter String registryNumber;

    public Helicopter (HelicopterBuilder builder) {
        this.setName(builder.getName());
        this.setFlightRange(builder.getFlightRange());
        this.setTotalCapacity(builder.getTotalCapacity());
        this.setCarryingCapacity(builder.getCarryingCapacity());
        this.setFuelConsumption(builder.getFuelConsumption());
        this.setAirlineCompany(builder.getAirlineCompany());
        this.setRegistryNumber(builder.getRegistryNumber());
    }

}
