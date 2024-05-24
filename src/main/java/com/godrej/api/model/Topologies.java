package com.godrej.api.model;
import jakarta.persistence.*; // for Spring Boot 3
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "topology")
public class Topologies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String configurations;
    private String aggreement_value;
    private String token_amount;
}
