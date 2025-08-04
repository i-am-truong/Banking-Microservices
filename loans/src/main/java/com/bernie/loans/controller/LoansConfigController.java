package com.bernie.loans.controller;

import com.bernie.loans.dto.LoansConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties(value = {LoansConfigDto.class})
public class LoansConfigController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private LoansConfigDto loansConfigDto;

    @GetMapping("/config-info")
    public LoansConfigDto getConfigInfo() {
        return loansConfigDto;
    }

    @GetMapping("/application-name")
    public String getApplicationName() {
        return applicationName;
    }
    
}