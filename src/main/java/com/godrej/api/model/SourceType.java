package com.godrej.api.model;

import jakarta.persistence.*; // for Spring Boot 3
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "trans_sourcetype")
public class SourceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sourceid;
    private String sourcetypecode;
    private String sourcename;
    private Integer active;
    private String createdby;
    private String createdon;
    private String updatedby;
    private String updatedon;
}
