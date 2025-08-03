package com.bernie.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @NotEmpty(message = "Account number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    @Schema(
            description = "Account number of Bernie accounts",
            example = "1234567890"
    )
    private Long accountNumber;

    @NotEmpty(message = "AccountType cannot be null or empty")
    @Schema(description = "Account type of Bernie accounts",
            example = "Savings")
    private String accountType;

    @NotEmpty(message = "Branch address cannot be null or empty")
    @Schema(description = "Branch address of Bernie accounts",
            example = "123 Main St, Springfield, USA")
    private String branchAddress;

}
