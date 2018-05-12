package com.andy.airline.controller;

import com.andy.airline.domain.AirlineCompany;
import com.andy.airline.service.AirlineCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.andy.airline.controller.AirlineCompanyController.Links.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class AirlineCompanyController {

    @Autowired
    AirlineCompanyService airlineCompanyService;

    @GetMapping(GET_COMPANY_PATH)
    public List<AirlineCompany> getAllCompanies() {
        return airlineCompanyService.getAllCompanies();
    }

    @GetMapping(GET_COMPANY_BY_ID_PATH)
    public ResponseEntity<AirlineCompany> getCompanyById(@PathVariable("id") Long id) {
        return airlineCompanyService.getCompanyById(id);
    }

    @PostMapping(CREATE_COMPANY_PATH)
    public AirlineCompany createCompany(@Valid @RequestBody AirlineCompany airlineCompany) {
        return airlineCompanyService.createCompany(airlineCompany);
    }

    @PutMapping(UPDATE_COMPANY_PATH)
    public ResponseEntity<AirlineCompany> updateCompany(@PathVariable("id") Long id, @Valid @RequestBody AirlineCompany airlineCompany) {
        return airlineCompanyService.updateCompany(id, airlineCompany);
    }

    @DeleteMapping(DELETE_COMPANY_PATH)
    public ResponseEntity<?> deleteCompany(@PathVariable("id") Long id) {
        return airlineCompanyService.deleteCompany(id);
    }

    public static class Links {
        static final String GET_COMPANY_PATH = "/api/company";
        static final String GET_COMPANY_BY_ID_PATH = "/api/company/{id}";
        static final String CREATE_COMPANY_PATH = "/api/company";
        static final String UPDATE_COMPANY_PATH = "/api/company/{id}";
        static final String DELETE_COMPANY_PATH = "/api/company/{id}";
    }


}
