package com.godrej.api.model;

import lombok.Data;
import lombok.NoArgsConstructor; 

@Data
@NoArgsConstructor
public class GetMobileAndOtp {
    private String mobileno;
    private int otp;
}
