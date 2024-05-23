package com.godrej.api.controller;

import com.godrej.api.repository.TopologiesRepository;
import com.godrej.api.model.Topologies;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/topologies")
public class TopologiesController {

    private final TopologiesRepository topologiesRepository;


    @PutMapping(value = "/updateTopology",produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateTopologies(@RequestBody Topologies topologies) {
        Topologies reqTopologies = topologies;

        Topologies topologies_fromdb = topologiesRepository.findById( reqTopologies.getId()).orElse(null);
        System.out.println("topologies_fromdb" + topologies_fromdb.toString());
        if (topologies_fromdb != null) {
            topologiesRepository.save(reqTopologies);
        } 
    }
    @GetMapping(value = "/topologies",produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Topologies> getAllTopologies() {
        return this.topologiesRepository.findAll();
    }




    @PostMapping(value = "/addTopologies",produces = MediaType.APPLICATION_JSON_VALUE)
    public Topologies addTopologies(@RequestBody Topologies topologies) {
        return this.topologiesRepository.save(topologies);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTopology(@PathVariable Integer id) {
        topologiesRepository.deleteByName(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
