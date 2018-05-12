package com.andy.airline.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "aircraft")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "type")

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TurboPropPlane.class),
        @JsonSubTypes.Type(value = JetPlane.class),
        @JsonSubTypes.Type(value = Helicopter.class),
})

public abstract class Aircraft implements Flyable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private @Setter String name;

    @Column(name = "flight_range")
    private @Setter int flightRange;

    @Column(name = "total_capacity")
    private @Setter int totalCapacity;

    @Column(name = "carrying_capacity")
    private @Setter int carryingCapacity;

    @Column(name = "fuel_consumption")
    private @Setter double fuelConsumption;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private @Setter AirlineCompany airlineCompany;

    @JsonIgnore
    @Transient
    private @Setter boolean inTheAir;

    @Override
    public void takeOff() {
        setInTheAir(true);
    }

    @Override
    public void land() {
        setInTheAir(false);
    }

}
