package com.godrej.api.model;

import jakarta.persistence.*; // for Spring Boot 3

import lombok.AllArgsConstructor;
import lombok.Data; 

@Entity
@Data
@AllArgsConstructor
public class ChannelPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
