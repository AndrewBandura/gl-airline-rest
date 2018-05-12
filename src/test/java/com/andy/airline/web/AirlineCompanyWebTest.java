package com.andy.airline.web;

import com.andy.airline.abstract_test.AbstractWebTest;
import com.andy.airline.domain.Aircraft;
import com.andy.airline.domain.AirlineCompany;
import com.andy.airline.domain.builder.HelicopterBuilder;
import com.andy.airline.domain.builder.JetPlaneBuilder;
import com.andy.airline.domain.builder.TurboPropPlaneBuilder;
import com.andy.airline.repository.AirlineCompanyRepository;
import com.andy.airline.service.AirlineCompanyService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ActiveProfiles("test")
public class AirlineCompanyWebTest extends AbstractWebTest {

    private static final Long NON_EXISTENT_AIRLINE_ID = 404L;
    private static final String GET_AIRLINES_PATH = "/api/company";
    private  String AIRLINES_PATH_WITH_ID;

    AirlineCompany testAirline;

    Set<Aircraft> aircraft;

    @Autowired
    AirlineCompanyService airlineCompanyService;

    @Autowired
    AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {

        testAirline = new AirlineCompany("Cantas");

        JetPlaneBuilder jetPlaneBuilder = new JetPlaneBuilder();
        TurboPropPlaneBuilder turboPropPlaneBuilder = new TurboPropPlaneBuilder();
        HelicopterBuilder helicopterBuilder = new HelicopterBuilder();

        Aircraft jetPlane1 = jetPlaneBuilder.setName("Boeing-737").setFlightRange(4000).setTotalCapacity(155)
                .setCarryingCapacity(150).setFuelConsumption(1000).setEngineNumber(2).setAirlineCompany(testAirline).build();

        Aircraft jetPlane2 = turboPropPlaneBuilder.setName("AN-22").setFlightRange(1000).setTotalCapacity(55)
                .setCarryingCapacity(50).setFuelConsumption(300.7).setEngineNumber(2).setAbleToLandOnUnpavedRoad(true).setAirlineCompany(testAirline).build();

        Aircraft turboPlane = jetPlaneBuilder.setName("Airbus-180").setFlightRange(2000).setTotalCapacity(100)
                .setCarryingCapacity(95).setFuelConsumption(700.2).setEngineNumber(2).setAirlineCompany(testAirline).build();

        Aircraft helicopter = helicopterBuilder.setName("Mi-6").setFlightRange(500).setTotalCapacity(10)
                .setCarryingCapacity(8).setFuelConsumption(200).setRegistryNumber("5555555").setAirlineCompany(testAirline).build();

        aircraft= new HashSet<>();
        aircraft.add(turboPlane);
        aircraft.add(jetPlane1);
        aircraft.add(jetPlane2);
        aircraft.add(helicopter);

        testAirline.setAircraft(aircraft);

        testAirline = airlineCompanyRepository.save(testAirline);

        AIRLINES_PATH_WITH_ID = GET_AIRLINES_PATH + "/"+ testAirline.getId();

    }

    @After
    public void tearDown() {
        airlineCompanyRepository.deleteAll();
    }

    @Test
    public void getAllCompaniesTest(){
        ResponseEntity<List<AirlineCompany>> responseEntity = restTemplate
                .exchange(GET_AIRLINES_PATH, HttpMethod.GET, HttpEntity.EMPTY,
                        new ParameterizedTypeReference<List<AirlineCompany>>() {
                        });

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().size() > 0);
    }

    @Test
    public void getCompanyByIdTest(){

        ResponseEntity<AirlineCompany> responseEntity = restTemplate
                .exchange(AIRLINES_PATH_WITH_ID, HttpMethod.GET, HttpEntity.EMPTY,
                        new ParameterizedTypeReference<AirlineCompany>() {
                        });

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testAirline.getId(), responseEntity.getBody().getId());
    }

    @Test
    public void createCompanyTest(){

        AirlineCompany newAirline = new AirlineCompany("new company");

        ResponseEntity<AirlineCompany> responseEntity = restTemplate
                .postForEntity(GET_AIRLINES_PATH, newAirline, AirlineCompany.class);
        AirlineCompany persistedAirline = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newAirline.getName(), persistedAirline.getName());
    }

    @Test
    public void updateCompanyTest(){

        AirlineCompany newAirline = new AirlineCompany("new company");
        newAirline.setAircraft(testAirline.getAircraft());

        HttpEntity<AirlineCompany> entity = new HttpEntity<>(newAirline);
        ResponseEntity<AirlineCompany> responseEntity = restTemplate
                .exchange(AIRLINES_PATH_WITH_ID, HttpMethod.PUT, entity, AirlineCompany.class, testAirline.getId());
        AirlineCompany persistedAirline = responseEntity.getBody();


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newAirline.getName(), persistedAirline.getName());
    }

    @Test
    public void deleteExistedCompanyShouldDeleteIt_Test() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", testAirline.getId());
        restTemplate.delete(AIRLINES_PATH_WITH_ID, map);

        assertEquals(Optional.empty(), airlineCompanyRepository.findById(testAirline.getId()));
    }

    @Test
    public void deleteNonexistentCompanyShouldReturnNOT_FOUND_Test() {
        final String URL = GET_AIRLINES_PATH + "/"+ NON_EXISTENT_AIRLINE_ID;
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(URL, HttpMethod.DELETE, HttpEntity.EMPTY, String.class, NON_EXISTENT_AIRLINE_ID);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }



}
