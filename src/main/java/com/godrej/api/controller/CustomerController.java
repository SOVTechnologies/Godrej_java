package com.godrej.api.controller;

import com.godrej.api.model.PaymentDetails;
import com.godrej.api.repository.CustomerRepository;
import com.godrej.api.model.CustomerIDAndMobile;
import com.godrej.api.model.TransCustomer;
import com.godrej.api.model.UploadPan;
import java.util.*;

import com.godrej.api.repository.PaymentRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {
@Autowired
    private CustomerRepository customerRepository;
    private PaymentRepository paymentRepository;


    @DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteByCustomerId(@PathVariable String id) {
         customerRepository.deleteByCustomerId(id);
    }
    @GetMapping(value = "/getAllCustomer",produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<TransCustomer> findAllCustomer() {
        return customerRepository.findAll();
    }

    @PostMapping(value = "/addCustomer",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransCustomer> addCustomer(@RequestBody TransCustomer customer) throws UnirestException {

        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        List<TransCustomer> existingCustomer = customerRepository.findByMobile(customer.getMobile());
        Integer id  = existingCustomer.get(0).getCustomer_id();
        PaymentDetails payment = (PaymentDetails) paymentRepository.findByCID(String.valueOf(id));

        if(existingCustomer.isEmpty() && payment.getCustomer_id().isEmpty())
        {

            customer.setCustomer_id(number);
            customerRepository.save(customer);

            HttpResponse<String> response = Unirest.get("https://buzzify.in/V2/http-api.php?apikey=tevF3ldEUBoXS8nd&senderid=GODREJ&number="+customer.getMobile()
                            +"&message=Hello%2C%20Greetings%20from%20Godrej%20Properties.%20Kindly%20verify%20the%20details%3A"+id.toString()+"%0A%0AMahaRERA%3A%20P52000055348%20%0Ahttp%3A%2F%2Fmaharera.mahaonline.gov.in.%0A%2AT%26C&format=json")
                    .asString();


            return new ResponseEntity<>(
                    customer,
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(
                existingCustomer.get(0),
                HttpStatus.OK);
    }
    @PutMapping(value = "/updateCustomer",produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateCustomer(@RequestBody TransCustomer customer) {
        TransCustomer reqCustomer = customer;

        TransCustomer existingCustomer = customerRepository.findById( reqCustomer.getCustomer_id()).orElse(null);
        System.out.println("existingCustomer" + existingCustomer.toString());
        customerRepository.save(reqCustomer);
    }

    @GetMapping(value = "/mobile/{mobile}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransCustomer> byMobile(@PathVariable String mobile) {

        List<TransCustomer> existingCustomer = customerRepository.findByMobile(mobile);

        return existingCustomer;
    }


    @PostMapping(value = "/updatePan",produces = MediaType.APPLICATION_JSON_VALUE)
    public TransCustomer updatePan(@RequestBody UploadPan customer) {
        UploadPan reqCustomer = customer;
        TransCustomer existingCustomer = customerRepository.findById( reqCustomer.getCustomer_id()).orElse(null);
        existingCustomer.setPanimage(customer.getPanimage());
        customerRepository.save(existingCustomer);
        return existingCustomer;
    }


    @GetMapping(value = "/encryptCustomer/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String encryptCustomer(@PathVariable Integer id) {

        TransCustomer existingCustomer = customerRepository.findById( id).orElse(null);
        CustomerIDAndMobile customerIDAndMobile = new CustomerIDAndMobile();
        customerIDAndMobile.setCustomer_id(id);
        customerIDAndMobile.setMobile(existingCustomer.getMobile());

        String value  = Base64.getEncoder().encodeToString(customerIDAndMobile.toString().getBytes());
        existingCustomer.setEncryptValue(value);
        customerRepository.save(existingCustomer);
        return value;
    }
}
