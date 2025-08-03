package com.bernie.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold successful response details"
)

@Data @AllArgsConstructor
public class ResponseDto {

    @Schema(
            description = "Status code representing the response"
    )
    private String statusCode;

    @Schema(
            description = "Status message representing the response"
    )
    private String statusMsg;
}
