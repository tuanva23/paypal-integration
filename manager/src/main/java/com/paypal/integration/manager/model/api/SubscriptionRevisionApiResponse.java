package com.paypal.integration.manager.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubscriptionRevisionApiResponse {
    @JsonProperty("redirect_url")
    private String redirectUrl;
}
