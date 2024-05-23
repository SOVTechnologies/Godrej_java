package com.godrej.api.controller;

import com.godrej.api.repository.CountryRepository;
import com.godrej.api.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/country")
public class CountryController  {
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping(value = "/countries",produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Country> findAllCountry() {
        return countryRepository.findAll();
    }

    @PostMapping(value = "/addCountry",produces = MediaType.APPLICATION_JSON_VALUE)
    public Country addCountry(@RequestBody Country country) {
       return countryRepository.save(country);
    }


    @GetMapping(value = "/findByCountry/{countryName}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Country> findStateByCountryName(@PathVariable String countryName) {
        return countryRepository.findStateByCountry(countryName);
    }


    @GetMapping(value = "/findByState/{stateName}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Country> findCityByState(@PathVariable String stateName) {
        return countryRepository.findCityByState(stateName);
    }
}
