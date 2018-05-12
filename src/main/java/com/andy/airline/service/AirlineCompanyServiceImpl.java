package com.andy.airline.service;



import com.andy.airline.domain.Aircraft;
import com.andy.airline.domain.AirlineCompany;
import com.andy.airline.repository.AircraftRepository;
import com.andy.airline.repository.AirlineCompanyRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AirlineCompanyServiceImpl implements AirlineCompanyService {

    @Autowired
    AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    AircraftRepository aircraftRepository;

    @Override
    public List<AirlineCompany> getAllCompanies() {
        return airlineCompanyRepository.findAll();
    }

    @Override
    public ResponseEntity<AirlineCompany> getCompanyById(long id) {
        return airlineCompanyRepository.findById(id)
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public AirlineCompany createCompany(AirlineCompany airlineCompany) {
        AirlineCompany company = airlineCompanyRepository.save(airlineCompany);
        company.getAircraft().forEach(aircraft -> {
            aircraft.setAirlineCompany(company);
            aircraftRepository.save(aircraft);
        });

        return company;
    }

    @Override
    public ResponseEntity<AirlineCompany> updateCompany(long id, AirlineCompany airlineCompany) {
        return airlineCompanyRepository.findById(id)
                .map(company -> {
                    company.setName(airlineCompany.getName());
                    company.setAircraft(airlineCompany.getAircraft());
                    AirlineCompany updatedCompany = airlineCompanyRepository.save(company);
                    return ResponseEntity.ok().body(updatedCompany);
                }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deleteCompany(long id) {
        return airlineCompanyRepository.findById(id)
                .map(todo -> {
                    airlineCompanyRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public int calculateTotalCapacity(AirlineCompany airlineCompany) {

        int totalCapacity = 0;
        for (Aircraft aircraft : airlineCompany.getAircraft()) {
            totalCapacity += aircraft.getTotalCapacity();
        }
        return totalCapacity;
    }

    public int calculateCarryingCapacity(AirlineCompany airlineCompany) {

        int carryingCapacity = 0;
        for (Aircraft aircraft : airlineCompany.getAircraft()) {
            carryingCapacity += aircraft.getCarryingCapacity();
        }
        return carryingCapacity;
    }

    public List<Aircraft> getSortedAircraftList(AirlineCompany airlineCompany){

        List<Aircraft> sortedAircraft = new ArrayList<>(airlineCompany.getAircraft());
        sortedAircraft.sort(Comparator.comparingInt(Aircraft::getFlightRange));

        return sortedAircraft;
    }

    public List<Aircraft> findByFuelConsumptionRange(AirlineCompany airlineCompany, double from, double to){

        return airlineCompany.getAircraft().stream().filter((a)->a.getFuelConsumption()>=from && a.getFuelConsumption()<=to).collect(Collectors.toList());

    }
}
