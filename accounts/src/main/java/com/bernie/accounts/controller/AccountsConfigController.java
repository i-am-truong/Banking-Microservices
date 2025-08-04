package com.bernie.accounts.controller;

import com.bernie.accounts.dto.AccountsConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@EnableConfigurationProperties(value = {AccountsConfigDto.class})
public class AccountsConfigController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private AccountsConfigDto accountsConfigDto;

    @GetMapping("/config-info")
    public AccountsConfigDto getConfigInfo() {
        return accountsConfigDto;
    }

    @GetMapping("/application-name")
    public String getApplicationName() {
        return applicationName;
    }
    
}