package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Amount {
    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("value")
    private String value;
}
