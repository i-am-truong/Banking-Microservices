package com.bernie.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer", example = "Bernie Truong"
    )
    @NotEmpty(message = "Name cannot be a null or empty")
    @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "bernie@gmail.com"
    )
    @NotEmpty(message = "Email address cannot be a null or empty")
    @Email(message = "Email address must be valid")
    private String email;

    @Schema(
            description = "Mobile number of the customer", example = "1234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account dètails of the customer")
    private AccountsDto accountsDto;

}
