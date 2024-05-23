package com.godrej.api.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.godrej.api.model.*;
import com.godrej.api.repository.CustomerRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class WrapperController {

    private final ObjectMapper objectMapper;
    private final CustomerRepository customerRepository;

    @PostMapping("/sendPancardOTP")
    public Object panEncrypt(@RequestBody PanEncrypt panEncrypt) throws UnirestException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        // Convert JSON to String
        String jsonAsString = objectMapper.writeValueAsString(panEncrypt);

//        System.out.println("JSON as String: " + jsonAsString);

        String url = "https://www.truthscreen.com/InstantSearch/encrypted_string";
        String requestDataUrl = "https://www.truthscreen.com/api/v2.2/idsearch";
        String decryptUrl = "https://www.truthscreen.com/InstantSearch/decrypt_encrypted_string";
        // Send POST request with headers
        HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").header("username", "production@godrejproperties.com").body(jsonAsString).asString();

        // Print response
        String encryptResponse = response.getBody();


        RequestData requestData = new RequestData();
        requestData.setRequestData(encryptResponse);
        String requestData_jsonAsString = objectMapper.writeValueAsString(requestData);

        HttpResponse<String> requestDataUrl_resposne = Unirest.post(requestDataUrl).header("Content-Type", "application/json").header("username", "production@godrejproperties.com").body(requestData_jsonAsString).asString();

//        System.out.println("1" + requestData_jsonAsString);
//        System.out.println("requestDataUrl_resposne" + requestDataUrl_resposne.getBody());
        String requestDataUrlResposne = requestDataUrl_resposne.getBody();

        Decrypt decrypt = new Decrypt();
        decrypt.setResponseData(requestDataUrlResposne);
        String decrypt_jsonAsString = objectMapper.writeValueAsString(decrypt);
        HttpResponse<String> decryptHTTP = Unirest.post(decryptUrl).header("Content-Type", "application/json").header("username", "production@godrejproperties.com").body(requestDataUrlResposne).asString();

//        System.out.println("main response " + decryptHTTP.getBody());

        ObjectMapper objectMapper1 = new ObjectMapper();
        Object jsonObject = objectMapper1.readValue(decryptHTTP.getBody(), Object.class);
        return jsonObject;

    }


    @PostMapping("/sendAdharcardOTP")
    public Object aadharCard(@RequestBody AadharCard aadharCard) throws UnirestException, JsonProcessingException {

        System.out.println("aadhar " + aadharCard.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        // Convert JSON to String
        String jsonAsString = objectMapper.writeValueAsString(aadharCard);

        String encryptUrl = "https://www.truthscreen.com/v1/apicall/encrypt";

        HttpResponse<String> response = Unirest.post(encryptUrl).header("Content-Type", "application/json").header("username", "production@godrejproperties.com").body(jsonAsString).asString();

        String encryptResponse = response.getBody();


        String getOtp = "https://www.truthscreen.com/v1/apicall/nid/aadhar_get_otp";

        RequestData requestData = new RequestData();
        requestData.setRequestData(encryptResponse);
        HttpResponse<String> getotp_response = Unirest.post(getOtp).header("Content-Type", "application/json").header("username", "production@godrejproperties.com").body(objectMapper.writeValueAsString(requestData)).asString();

        String getotp_responseResponse = getotp_response.getBody();

        //decrypt https://www.truthscreen.com/v1/apicall/decrypt

        String decrypt = "https://www.truthscreen.com/v1/apicall/decrypt";
        HttpResponse<String> decrypt_response = Unirest.post(decrypt).header("Content-Type", "application/json").header("username", "production@godrejproperties.com").body(getotp_responseResponse).asString();
        String decrypt_response_body = decrypt_response.getBody();
        ObjectMapper objectMapper1 = new ObjectMapper();
        Object jsonObject = objectMapper1.readValue(decrypt_response_body, Object.class);
        return jsonObject;
//        return decrypt_response_body;

    }


    @PostMapping("/verifyAdharcardOTP")
    public Object verifyAdharcardOTP(@RequestBody TransWithOTP aadharCard) throws UnirestException, JsonProcessingException {

        System.out.println("aadhar " + aadharCard.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        // Convert JSON to String
        String jsonAsString = objectMapper.writeValueAsString(aadharCard);

        String encryptUrl = "https://www.truthscreen.com/v1/apicall/encrypt";

        HttpResponse<String> response = Unirest.post(encryptUrl).header("Content-Type", "application/json").header("username", "production@godrejproperties.com").body(jsonAsString).asString();

        String encryptResponse = response.getBody();


        String getOtp = "https://www.truthscreen.com/v1/apicall/nid/aadhar_submit_otp";

        RequestData requestData = new RequestData();
        requestData.setRequestData(encryptResponse);
        HttpResponse<String> getotp_response = Unirest.post(getOtp).header("Content-Type", "application/json").header("username", "production@godrejproperties.com").body(objectMapper.writeValueAsString(requestData)).asString();

        String getotp_responseResponse = getotp_response.getBody();

        //decrypt https://www.truthscreen.com/v1/apicall/decrypt

        String decrypt = "https://www.truthscreen.com/v1/apicall/decrypt";
        HttpResponse<String> decrypt_response = Unirest.post(decrypt).header("Content-Type", "application/json").header("username", "production@godrejproperties.com").body(getotp_responseResponse).asString();
        String decrypt_response_body = decrypt_response.getBody();
        ObjectMapper objectMapper1 = new ObjectMapper();
        Object jsonObject = objectMapper1.readValue(decrypt_response_body, Object.class);
        return jsonObject;
//        return decrypt_response_body;

    }

    @PostMapping("/verifyBroker")
    public Object empanelment(@RequestBody ApiContainer apiContainer) throws UnirestException, JsonProcessingException {


        ObjectMapper objectMapper = new ObjectMapper();

        String api_container_url = "https://api.godrejproperties.com:8085/api-container/signIn/authenticate";

        APIContainerData apiContainerData = new APIContainerData();
        apiContainerData.setEmail("gpl_eoi@onerooftech.com");
        apiContainerData.setPassword("Oneroof@123");

        System.out.println("apiContainerData" + objectMapper.writeValueAsString(apiContainerData));

        HttpResponse<com.mashape.unirest.http.JsonNode> response = Unirest.post(api_container_url).header("Content-Type", "application/json").body(objectMapper.writeValueAsString(apiContainerData)).asJson();

        System.out.println("response " + response.getBody().getObject().get("token"));




        HttpResponse<String> verify_broker_request;
        verify_broker_request = Unirest.post("https://api.godrejproperties.com:8085/api-container/verify_broker")
                .header("Authorization", "Bearer " + response.getBody().getObject().get("token"))
                .field("empid", apiContainer.getEmpid())
                .field("email", apiContainer.getEmail())
//                .body(objectMapper.writeValueAsString(apiContainer))
               .asString();


        Object jsonObject = objectMapper.readValue(verify_broker_request.getBody(), Object.class);
        return jsonObject;


    }

    @Data
    public class APIContainerData {
        private String email;
        private String password;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Token {
        private String token;
    }

    public JsonNode convertToJsonObject( String jsonString) {
        try {
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle error appropriately
        }
    }





    @PostMapping("/getCPSourceProtection")
    public Object getCPSourceProtection(@RequestBody CPSource cpSource) throws UnirestException, JsonProcessingException {
        HttpResponse<String> request = Unirest.post("https://d4u.godrejproperties.com/walkinformuat/getCPSourceProtection")
                .header("Content-Type", "application/json")
                .header("username", "production@godrejproperties.com")
                .body(objectMapper.writeValueAsString(cpSource)).asString();
        String response = request.getBody();
        Object jsonObject = objectMapper.readValue(response, Object.class);
        return jsonObject;
    }

    //https://d4u.godrejproperties.com/walkinformuat/getenquirysourceprc/+91/9987677726/a1l6F000008DnniQAC
    @GetMapping("/getenquirysourceprc/{countrycode}/{mobile}/{pid}")
    public Object Getenquirysourceprc(@PathVariable String countrycode,@PathVariable String mobile,@PathVariable String pid) throws UnirestException, JsonProcessingException {




        String url = "https://d4u.godrejproperties.com/walkinformuat/getenquirysourceprc/%2B"+countrycode+"/"+mobile+"/"+pid;

        // Make the GET request
        HttpResponse<String> response = Unirest.get(url)
                .asString();

        String body = response.getBody();
        Object jsonObject = objectMapper.readValue(body, Object.class);
        return jsonObject;

    }

    @PostMapping("/voiceBroadcast")
    public Object voiceBroadcast(@RequestBody GetMobileAndOtp request) throws JsonProcessingException, UnirestException {

        String otpNumber = String.format("%04d", new Random().nextInt(10000));


        TransCustomer customer = customerRepository.findByMobileNo(request.getMobileno());
        if(customer !=null)
        {
            customer.setOtp(Integer.parseInt(otpNumber));
            this.customerRepository.save(customer);
        }

        HttpResponse<String> requestMethod = Unirest.post("http://voice1.buzzify.in/api/voice/voice_broadcast_tts.php")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("username",
                        "u13323")
                .field("contact_numbers",
                        request.getMobileno())
                .field("content",
                        "Welcome to Godrej Properties. Your OTP is "+otpNumber+". I repeat OTP is "+ otpNumber)

                .field("token",
                        "0K8Awr")

                .field("retry_json",
                        "{\"FNA\":\"1\",\"FBZ\":0,\"FCG\":\"2\",\"FFL\":\"1\"}")
                .field("caller_id",
                        "07447120620")
                .field("plan_id",
                        "30989")
                .asString();


        String response = requestMethod.getBody();


        Object jsonObject;
        jsonObject = objectMapper.readValue(response, Object.class);
        return jsonObject;
    }




    @PostMapping("/whatsappOtp")
    public Object otpRegister(@RequestBody GetMobileNumber request) throws JsonProcessingException, UnirestException {

        String otpNumber = String.format("%04d", new Random().nextInt(10000));


        TransCustomer customer = customerRepository.findByMobileNo(request.getNumber());
        if(customer !=null)
        {
            customer.setOtp(Integer.parseInt(otpNumber));
            this.customerRepository.save(customer);
        }




        String jsonString = "{"
                + "\"scenarioKey\": \"" + "B43574B2A1D9D35FEBEC690999303D6F" + "\","
                + "\"destinations\": ["
                + "{"
                + "\"to\": {"
                + "\"phoneNumber\": \"" + request.getCountry_code() + request.getNumber() + "\""
                + "}"
                + "}"
                + "],"
                + "\"whatsApp\": {"
                + "\"templateName\": \"" + "du_otp_registration" + "\","
                + "\"templateNamespace\": \"134d127c_84ba_467a_adf2_d8b8e5592b0c\","
                + "\"templateData\": [\"" + otpNumber + "\",\"'\",\"" + "Customer" + "\",\"'\"],"
                + "\"language\":\"en\""
                + "}"
                + "}";


        Unirest.setTimeouts(0, 0);
        HttpResponse<String> requestMethod = Unirest.post("https://dp638.api.infobip.com/omni/1/advanced")
                .header("WHATSAPP_LOGINURL", "https://dp638.api.infobip.com/omni/1/scenarios")
                .header("WHATSAPP_USERNAME", "godrejwa")
                .header("WHATSAPP_PASSWORD", "Password@123")
                .header("scenarioKey", "B43574B2A1D9D35FEBEC690999303D6F")

                .header("Content-Type", "application/json")
                .header("Authorization", "Basic Z29kcmVqd2E6UGFzc3dvcmRAMTIz")
                .body(jsonString)
                .asString();

        String response = requestMethod.getBody();


        return objectMapper.readValue(response, Object.class);
    }


}



