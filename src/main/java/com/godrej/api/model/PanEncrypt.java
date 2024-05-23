package com.godrej.api.model;

import lombok.Data;

@Data
public class PanEncrypt {
    private String transID;
    private int docType;
    private String docNumber;
}
