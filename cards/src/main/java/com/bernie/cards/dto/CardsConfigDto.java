package com.bernie.cards.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "cards")
@Getter @Setter
public class CardsConfigDto {

    private String message;
    private Map<String, String> contactDetails;
    
}