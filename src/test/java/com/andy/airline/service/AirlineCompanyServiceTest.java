package com.andy.airline.service;

import com.andy.airline.domain.Aircraft;
import com.andy.airline.domain.AirlineCompany;
import com.andy.airline.domain.builder.JetPlaneBuilder;
import com.andy.airline.domain.builder.TurboPropPlaneBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
public class AirlineCompanyServiceTest {

    private AirlineCompany airlineCompany;
    private AirlineCompanyService airlineService;

    @Before
    public void setUp() {

        airlineService = new AirlineCompanyServiceImpl();
        airlineCompany = new AirlineCompany("Cantas");

        JetPlaneBuilder jetPlaneBuilder = new JetPlaneBuilder();
        TurboPropPlaneBuilder turboPropPlaneBuilder = new TurboPropPlaneBuilder();

        Aircraft jetPlane1 = jetPlaneBuilder.setName("Boeing-737").setFlightRange(4000).setTotalCapacity(155)
                .setCarryingCapacity(150).setFuelConsumption(1000).setAirlineCompany(airlineCompany).build();

        Aircraft jetPlane2 = turboPropPlaneBuilder.setName("AN-22").setFlightRange(1000).setTotalCapacity(55)
                .setCarryingCapacity(50).setFuelConsumption(300.7).setAirlineCompany(airlineCompany).build();

        Aircraft turboPlane = jetPlaneBuilder.setName("Airbus-180").setFlightRange(2000).setTotalCapacity(100)
                .setCarryingCapacity(95).setFuelConsumption(700.2).setAirlineCompany(airlineCompany).build();

        Set<Aircraft> aircraft= new HashSet<>();
        aircraft.add(turboPlane);
        aircraft.add(jetPlane1);
        aircraft.add(jetPlane2);

        airlineCompany.setAircraft(aircraft);

    }

    @Test
    public void calculateTotalCapacity(){
        final int expected = 310;
        int actual = airlineService.calculateTotalCapacity(airlineCompany);
        assertEquals(expected, actual);
    }

    @Test
    public void calculateCarryingCapacity(){
        final int expected = 295;
        int actual = airlineService.calculateCarryingCapacity(airlineCompany);
        assertEquals(expected, actual);
    }

    @Test
    public void getSortedAircraftList(){
        final int expectedMin = 1000;
        final int expectedMax = 4000;
        List<Aircraft> sortedList = airlineService.getSortedAircraftList(airlineCompany);
        assertEquals(expectedMin, sortedList.get(0).getFlightRange());
        assertEquals(expectedMax, sortedList.get(2).getFlightRange());
    }

    @Test
    public void findByFuelConsumptionRangeShouldReturnOneItem(){
        final double from = 100;
        final double to = 500;
        int expectedSize = 1;
        List<Aircraft> foundList = airlineService.findByFuelConsumptionRange(airlineCompany, from, to);
        assertEquals(expectedSize, foundList.size());
    }
}
