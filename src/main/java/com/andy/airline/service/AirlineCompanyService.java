package com.andy.airline.service;

import com.andy.airline.domain.Aircraft;
import com.andy.airline.domain.AirlineCompany;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Andrew Bandura
 */

public interface AirlineCompanyService{

        List<AirlineCompany> getAllCompanies();
        ResponseEntity<AirlineCompany> getCompanyById(long id);
        AirlineCompany createCompany(AirlineCompany company);
        ResponseEntity<AirlineCompany> updateCompany(long id, AirlineCompany company);
        ResponseEntity<?> deleteCompany(long id);

        /**
         * Returns a calculated total capacity of all aircraft.
         * @return an int value.
         */
        int calculateTotalCapacity(AirlineCompany airlineCompany);

        /**
         * Returns a calculated carrying capacity of all aircraft.
         * @return an int value.
         */
        int calculateCarryingCapacity(AirlineCompany airlineCompany);

        /**
         * Returns an aircraft list sorted by flight range.
         * @return a list of Aircraft.
         */
        List<Aircraft> getSortedAircraftList(AirlineCompany airlineCompany);

        /**
         * Returns an aircraft list filtered by fuel consumption range.
         *
         * @param from the double value, min in the range.
         * @param to   the double value, max in the range.
         * @return a list of Aircraft.
         */
        List<Aircraft> findByFuelConsumptionRange(AirlineCompany airlineCompany, double from, double to);

}
