package com.godrej.api.model;

import jakarta.persistence.*; // for Spring Boot 3
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
//@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String country;
    private int population;
    private String state;
    private String capital;
    private String currency;
    private String city;
    
}
