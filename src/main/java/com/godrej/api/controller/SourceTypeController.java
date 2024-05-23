package com.godrej.api.controller;

import com.godrej.api.model.SourceType;
import com.godrej.api.repository.SourceTypeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SourceTypeController {

    private final SourceTypeRepository sourceTypeRepository;

    @GetMapping(value = "/sourcetypes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<SourceType> findAllSourceType() {
        return this.sourceTypeRepository.findAll();
    }

    @PostMapping(value = "/addSourceType", produces = MediaType.APPLICATION_JSON_VALUE)
    public SourceType addSourceType(@RequestBody SourceType sourceType) {
        return this.sourceTypeRepository.save(sourceType);
    }
}
