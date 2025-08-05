package com.bernie.accounts;

import com.bernie.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImplement")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                description = "BernieBank Accounts microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Bernie Truong",
                        email = "bernie@gmail.com",
                        url = "https://bernietruong.id.vn"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://bernietruong.id.vn"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description =  "BernieBank Accounts microservice REST API Documentation",
                url = "https://bernietruong.id.vn/swagger-ui.html"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
