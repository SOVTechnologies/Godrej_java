package com.godrej.api.model;

import lombok.Data;

@Data
public class OTPResponse {
    private int statusCode = 200;
    private String msg  = "Success";

}
