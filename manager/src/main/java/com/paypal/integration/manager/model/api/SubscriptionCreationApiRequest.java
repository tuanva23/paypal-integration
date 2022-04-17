package com.paypal.integration.manager.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubscriptionCreationApiRequest {
    @JsonProperty("plan_id")
    private String planId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("quantity")
    private String quantity;
}
