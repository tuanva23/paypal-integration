package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Name {
    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("surname")
    private String surname;
}
