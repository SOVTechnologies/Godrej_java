package com.godrej.api.repository;

import com.godrej.api.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("SELECT c FROM Country c WHERE c.country = :country")
    List<Country> findStateByCountry(@Param("country") String country);


    @Query("SELECT c FROM Country c WHERE c.state = :state")
    List<Country> findCityByState(@Param("state") String state);
}
