package com.andy.airline;

import com.andy.airline.domain.Aircraft;
import com.andy.airline.domain.AirlineCompany;
import com.andy.airline.domain.JetPlane;
import com.andy.airline.repository.AircraftRepository;
import com.andy.airline.repository.AirlineCompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AirlineApplicationTests {

	@Autowired
	AircraftRepository aircraftRepository;
	@Autowired
	AirlineCompanyRepository airlineCompanyRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void createTest() {

		AirlineCompany company = new AirlineCompany();
		company.setName("RyanAir");
		airlineCompanyRepository.save(company);



		Aircraft jet = new JetPlane();
		jet.setTotalCapacity(500);
		jet.setAirlineCompany(company);
		aircraftRepository.save(jet);


		List<Aircraft> aircraftPersistant = aircraftRepository.findAll();


	}

}
