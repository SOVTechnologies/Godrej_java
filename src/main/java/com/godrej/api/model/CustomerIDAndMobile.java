package com.godrej.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerIDAndMobile {
    private Integer customer_id;
    private String mobile;
}
