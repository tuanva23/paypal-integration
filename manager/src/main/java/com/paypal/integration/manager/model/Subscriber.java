package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Subscriber {
    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("payer_id")
    private String payerId;

    @JsonProperty("name")
    private Name name;
}
