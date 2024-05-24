package com.godrej.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TbBroker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sfid;
    private String name;
    private String email;
    private String panNumber;
    private String brokerEmpanelmentId;
    private String mobileNo;
    private String propstrengthActive;
    private String accountId;

}
