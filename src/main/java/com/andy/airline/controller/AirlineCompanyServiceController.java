package com.andy.airline.controller;

import com.andy.airline.domain.Aircraft;
import com.andy.airline.domain.AirlineCompany;
import com.andy.airline.dto.AirlineServiceDto;
import com.andy.airline.repository.AirlineCompanyRepository;
import com.andy.airline.service.AirlineCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.andy.airline.controller.AirlineCompanyServiceController.Links.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class AirlineCompanyServiceController {

    @Autowired
    AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    AirlineCompanyService airlineCompanyService;

    @GetMapping(GET_CAPACITY_PATH)
    public ResponseEntity<AirlineServiceDto> geCapacity(@PathVariable("id") Long id) {
        Optional<AirlineCompany> company = airlineCompanyRepository.findById(id);
        if (!company.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Integer capacity = airlineCompanyService.calculateTotalCapacity(company.get());
            return ResponseEntity.ok().body(new AirlineServiceDto(id, capacity));
        }
    }

    @GetMapping(GET_CARRYING_CAPACITY_PATH)
    public ResponseEntity<AirlineServiceDto> geCarryingCapacity(@PathVariable("id") Long id) {
        Optional<AirlineCompany> company = airlineCompanyRepository.findById(id);
        if (!company.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Integer capacity = airlineCompanyService.calculateCarryingCapacity(company.get());
            return ResponseEntity.ok().body(new AirlineServiceDto(id, capacity));
        }
    }

    @GetMapping(CREATE_SORTED_PATH)
    public ResponseEntity<AirlineServiceDto> getSortedAircraft(@PathVariable("id") Long id) {
        Optional<AirlineCompany> company = airlineCompanyRepository.findById(id);
        if (!company.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            List<Aircraft> aircraft = airlineCompanyService.getSortedAircraftList(company.get());
            return ResponseEntity.ok().body(new AirlineServiceDto(id, aircraft));
        }
    }

    @GetMapping(GET_BY_FUEL_CONSUMPTION_PATH)
    public ResponseEntity<AirlineServiceDto> getByFuelConsumption(@PathVariable("id") Long id, @PathVariable("from") Double from, @PathVariable("to") Double to) {
        Optional<AirlineCompany> company = airlineCompanyRepository.findById(id);
        if (!company.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            List<Aircraft> aircraft = airlineCompanyService.findByFuelConsumptionRange(company.get(), from, to);
            return ResponseEntity.ok().body(new AirlineServiceDto(id, aircraft));
        }
    }

    public static class Links {
        static final String GET_CAPACITY_PATH = "/api/company/{id}/capacity";
        static final String GET_CARRYING_CAPACITY_PATH = "/api/company/{id}/carrying-capacity";
        static final String CREATE_SORTED_PATH = "/api/company/{id}/aircraft/sort/";
        static final String GET_BY_FUEL_CONSUMPTION_PATH = "/api/company/{id}/aircraft/consumption/{from}/{to}";
    }


}
