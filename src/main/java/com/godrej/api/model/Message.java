package com.godrej.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
@ToString
public class Message {
    private final String scenarioKey = "B43574B2A1D9D35FEBEC690999303D6F";
    @Getter
    @Setter
    private List<Destination> destinations;
    @Autowired
    private WhatsApp whatsApp = new WhatsApp();


}

