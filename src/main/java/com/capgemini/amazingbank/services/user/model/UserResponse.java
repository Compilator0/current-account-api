package com.capgemini.amazingbank.services.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Schema(description = "User")
@Data
public class UserResponse {
    @Schema(example = "cf8d3518-d503-449a-9093-3a4819b01589")
    private String identifier;
    @Schema(example = "idriscompil0@cab.com")
    private String email;
    @Schema(example = "Idris")
    private String firstName;
    @Schema(example = "COMPILATOR")
    private String surname;
}