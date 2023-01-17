package com.hoverfly.demo.controller;

import com.hoverfly.demo.dto.UniDTO;
import com.hoverfly.demo.service.UniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UniController {

    @Autowired
    private UniService uniService;

    @GetMapping("/api/university")
    public List<UniDTO> getUniversitiesForCountry(@RequestParam String country) {
        return uniService.getUniInfo(country);
    }
}
