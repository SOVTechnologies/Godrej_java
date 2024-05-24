package com.godrej.api.model;

import jakarta.persistence.*; // for Spring Boot 3
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "trans_customer")
@Component
public class TransCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customer_id;
    private String customeruniqueno = "";
    private String countrycode = "";
    private String mobile = "";
    private String customerfirstname = "";
    private String customerlastname = "";
    private String typologypreference = "";
    private String emailid = "";
    private int sourcetype = 0;
    private String source_name = "";
    private String address = "";
    private String pan = "";
    private String panimage = "";
    private String paymentconfirmation = "";
    private String aadhaarcardnumber = "";
    private String address1 = "";
    private String address2 = "";
    private String address3 = "";
    private String state = "";
    private String quantity = "";
    private String agreement_value="";
    private String country = "";
    private String city = "";
    private String pincode = "";
    private String paymenttranscationno = "";
    private int amount = 0;
    private String cp_email = "";
    private String cp_empanelmentnumber = "";
    private String createdby = "";
    private String createdon = "";
    private String updatedby = "";
    private String updatedon = "";
    private String channelPartner;
    private String channelPartnerName;
    private String channelName;
    private String projectName;
    private String projectMobileNumber;
    private String projectEmail;
    private String btlActivities;
    private String btlActivitiesDesc;
    private String referralValue;
    private int otp;
    private String encryptValue;
}
