package com.godrej.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResposne {
    private String referenceno= "000000";
    private String status="Error Processing Payment";
    private String statuscode = "0";
}
