package com.tech.test.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Class for data transfer object
 * for error controller response
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDTO {

    /**
     * Error code
     */
    @Schema(description = "Error code", example = "404")
    private Integer code;

    /**
     * Error message description
     */
    @Schema(description = "Error description", example = "Not found")
    private String message;

    /**
     * Level of error
     */
    @Schema(description = "Level of error", example = "ERROR")
    private String level;
}
