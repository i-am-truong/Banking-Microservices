package com.bernie.cards.controller;

import com.bernie.cards.dto.CardsConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties(value = {CardsConfigDto.class})
public class CardsConfigController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private CardsConfigDto cardsConfigDto;

    @GetMapping("/config-info")
    public CardsConfigDto getConfigInfo() {
        return cardsConfigDto;
    }

    @GetMapping("/application-name")
    public String getApplicationName() {
        return applicationName;
    }
    
}