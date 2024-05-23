package com.godrej.api.model;

import lombok.Data; 

@Data
public class GetMobileNumber {
    private  String number;
    private  String country_code;
    private  String Identifier;
}
