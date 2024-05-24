package com.godrej.api.controller;

import com.godrej.api.model.GetMobileAndOtp;
import com.godrej.api.model.GetMobileNumber;
import com.godrej.api.model.Otp;
import com.godrej.api.model.TransCustomer;
import com.godrej.api.repository.CustomerRepository;
import com.godrej.api.repository.OtpRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/")
public class OtpController {

    private final OtpRepository otpRepository;
    private final CustomerRepository customerRepository;

    @PostMapping("/isOTPValid")
    public String isOTPValid(@RequestBody GetMobileAndOtp otp) {

        TransCustomer result = customerRepository.findByMobileNo(otp.getMobileno());


        return result.getOtp() == otp.getOtp() ? "Found" : "Not Found";
    }

    @GetMapping(value = "/otpList", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Otp> findAll() {
        return this.otpRepository.findAll();
    }

    @PostMapping(value = "/sendOtp", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public String add(@RequestBody GetMobileNumber request) throws UnirestException {
        String otpNumber = String.format("%04d", new Random().nextInt(10000));
        String number = "91" + request.getNumber();
        Unirest.setTimeouts(0, 0);


        TransCustomer customer = customerRepository.findByMobileNo(request.getNumber());
        if(customer !=null)
        {
            customer.setMobile(request.getNumber());
            customer.setOtp(Integer.parseInt(otpNumber));
            this.customerRepository.save(customer);
        }
        else
        {
            TransCustomer new_customer  = new TransCustomer();
            new_customer.setMobile(request.getNumber());
            new_customer.setOtp(Integer.parseInt(otpNumber));
            this.customerRepository.save(new_customer);
        }
        if(!request.getCountry_code().equals("IN"))
        {

                HttpResponse<String> response =
                        Unirest.post("https://2factor.in/API/R1/").field("module", "TRANS_SMS")
                                .field("apikey", "a0b64286-2d78-11ee-addf-0200cd936042").field("to",
                                        number).field("from", "SOVTE")
                                .field("msg", "Hello, Greetings from Godrej Properties. Kindly verify the details: " + otpNumber+ "\n" +
                                        "\n" +
                                        "MahaRERA: P52000055348\n" +
                                        "http://maharera.mahaonline.gov.in.\n" +
                                        "*T&C").asString();
                return "Success";
        }
        else {

            HttpResponse<String> response = Unirest.get("https://buzzify.in/V2/http-api.php?apikey=tevF3ldEUBoXS8nd&senderid=GODREJ&number="+number+"&message=Hello%2C%20Greetings%20from%20Godrej%20Properties.%20Kindly%20verify%20the%20details%3A"+otpNumber+"%0A%0AMahaRERA%3A%20P52000055348%20%0Ahttp%3A%2F%2Fmaharera.mahaonline.gov.in.%0A%2AT%26C&format=json")
                    .asString();

            return "Success";

        }

    }


    @PostMapping(value = "/EOISendOtp", produces = MediaType.TEXT_PLAIN_VALUE)
    public String eoiOtp(@RequestBody GetMobileNumber request) throws UnirestException {
        String otpNumber = String.format("%04d", new Random().nextInt(10000));
        String number = "91" + request.getNumber();


        HttpResponse<String> response = Unirest.get("https://buzzify.in/V2/http-api.php?apikey=tevF3ldEUBoXS8nd&senderid=GODREJ&number="+number+"&message=Dear%20Customer%2C%20Your%20OTP%20to%20access%20EOI%20Application%20is%20"+otpNumber+".%20Kindly%20share%20this%20code%20with%20your%20Channel%20Partner%20%3C%201234%20%3E%20to%20confirm%20your%20interest%20in%20sample%20Regards%2C%20Godrej%20Properties%20Limited&format=json")

                .asString();
            System.out.println(response);

        return "Success";
    }


    @PostMapping(value = "/otpVerification", produces = MediaType.TEXT_PLAIN_VALUE)
    public String Otpverification(@RequestBody GetMobileNumber request) throws UnirestException {
        String otpNumber = String.format("%04d", new Random().nextInt(10000));
        String number = "91" + request.getNumber();


            HttpResponse<String> response = Unirest.get("https://buzzify.in/V2/http-api.php?apikey=tevF3ldEUBoXS8nd&senderid=GODREJ&number="+number+"&message="+otpNumber+"%20is%20your%20OTP%20for%20verification.%20OTP%20is%20confidential.%20For%20security%20reasons%2C%20DO%20NOT%20SHARE%20THIS%20OTP%20WITH%20ANYONE.%20Regards%2C%20Godrej%20Properties&format=json\n")

                .asString();
        System.out.println(response.getBody());

        return "Success";
    }


}

