package com.godrej.api.controller;

import com.godrej.api.repository.TbBrokerRepository;
import com.godrej.api.model.TbBroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/broker")
public class TbBrokerController {
@Autowired
    private TbBrokerRepository repo;


    @DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletById(@PathVariable Integer id) {
         repo.deletById(id);
    }
    @GetMapping(value = "/getAllBroker",produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<TbBroker> findAll() {
        return repo.findAll();
    }

    @PostMapping(value = "/addBroker",produces = MediaType.APPLICATION_JSON_VALUE)
    public TbBroker addBroker(@RequestBody TbBroker tbBroker) {
        return repo.save(tbBroker);
    }


    //findByName
    @GetMapping(value = "/findByName/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TbBroker> addBroker(@PathVariable String name) {
        return repo.findByName(name);
    }
    @PutMapping(value = "/updateBroker",produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateBroker(@RequestBody TbBroker tbBroker) {
        TbBroker tbBroker1 = tbBroker;

        TbBroker existingCustomer = repo.findById( tbBroker1.getId()).orElse(null);
        System.out.println("existingCustomer" + existingCustomer.toString());
        repo.save(tbBroker1);
    }
}
