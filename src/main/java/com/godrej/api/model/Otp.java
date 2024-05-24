package com.godrej.api.model;

import jakarta.persistence.*; // for Spring Boot 3
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "trans_otp")
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tranotpid;
    private String mobileno = "";
    private String otp="";
    private String validfor="";
    private String insertedon="";
}
