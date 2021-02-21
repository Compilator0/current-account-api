package com.capgemini.amazingbank.services.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Schema
@Data
public class UserSearchCriteriaInput {
    @Schema(description = "A user identifier", example = "cf23ff12-e26b-4e5a-b71a-ebc80d8da0b5")
    private String identifier;

    @Schema(description = "A user first name", example = "Idris")
    private String firstName;

    @Schema(description = "A user surname", example = "COMPILATOR")
    private String surname;

    @Schema(description = "A user email", example = "idriscompil0@cab.com")
    private String email;
}
