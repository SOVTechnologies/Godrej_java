package com.godrej.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderId;
    private double amount;
    private String currency;
    private String accessCode;
    private String customer_id;
    private String payment_mode;
    private String payment_status;
    private String trackingId;
    private String orderStatus;
    private String statusCode;
    private String statusMessage;
    private String mobile;

}
