package com.godrej.api.model;

import lombok.Data;

@Data
public class TransWithOTP {
    private String transId;
    private int otp;
}
