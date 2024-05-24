package com.godrej.api.controller;

import com.godrej.api.model.PaymentResposne;
import com.godrej.api.model.TransCustomer;
import com.godrej.api.repository.CustomerRepository;
import com.godrej.api.repository.PaymentRepository;
import com.godrej.api.repository.TopologiesRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

 

import com.godrej.api.model.PaymentDetails;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment/")
public class PaymentController {


    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final TopologiesRepository topologiesRepository;

    EmailController emailController;

    @PostMapping(value = "/savePaymentInfo",produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentResposne makePayment(@RequestBody PaymentDetails request) throws UnirestException, MessagingException {


        int count = paymentRepository.findBystatusCode(String.valueOf("1")).size();
        if(count ==0){
            count = 500;
        }
        PaymentResposne paymentResposne = new PaymentResposne();
        TransCustomer customer =  customerRepository.findByCID(request.getCustomer_id());

        if(request.getStatusCode().equals("1") || request.getPayment_status().equals("SUCCESS") || request.getStatusMessage().equals("SUCCESS"))
        {




            String plotNumber = String.format("%03d", count++);
            if(customer.getTypologypreference().contains("1200")){
                paymentResposne.setReferenceno("GHVOD0"+plotNumber);
            }
            if(customer.getTypologypreference().contains("1500")){
                paymentResposne.setReferenceno("GHVOC0"+plotNumber);
            }
            if(customer.getTypologypreference().contains("2000")){
                paymentResposne.setReferenceno("GHVOB0"+plotNumber);
            }
            if(customer.getTypologypreference().contains("2400")){
                paymentResposne.setReferenceno("GHVOA0"+plotNumber);
            }

            paymentResposne.setStatus(request.getStatusMessage());
            paymentResposne.setStatuscode(request.getStatusCode());
            customer.setPaymenttranscationno(request.getOrderId());
            request.setAccessCode(plotNumber);
            paymentRepository.save(request);


            HttpResponse<String> response = Unirest.get("https://buzzify.in/V2/http-api.php?apikey=tevF3ldEUBoXS8nd&senderid=GODREJ&number="+request.getMobile()+"&message=Hello%2C%20Greetings%20from%20Godrej%20Properties.%20Kindly%20verify%20the%20details%3AHEV"+paymentResposne.getReferenceno()+"%0A%0AMahaRERA%3A%20P52000055348%20%0Ahttp%3A%2F%2Fmaharera.mahaonline.gov.in.%0A%2AT%26C&format=json")
                    .asString();
        }
        else{
            paymentResposne.setReferenceno("HEV000");
            paymentResposne.setStatus(request.getStatusMessage());
            paymentResposne.setStatuscode(request.getStatusCode());
            paymentRepository.save(request);
        }


        emailController.sendEmail(customer,request.getTrackingId());
        return paymentResposne;
    }




    @GetMapping(value = "/getPaymentList")
    public Iterable<PaymentDetails> findAllCustomer() {
        int count = paymentRepository.findBystatusCode(String.valueOf("1")).size();

        System.out.println("counts...................."+count);
        return paymentRepository.findAll();
    }



    @GetMapping(value = "/getPaymentListByCID/{customer_id}")
    public Iterable<PaymentDetails> findByCID(@PathVariable("customer_id") String customer_id) {

        return paymentRepository.findByCID(customer_id);
    }


    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }



}



