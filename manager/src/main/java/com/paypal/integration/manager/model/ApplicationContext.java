package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApplicationContext {
    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("locale")
    private String locale;

    @JsonProperty("user_action")
    private String userAction;

    @JsonProperty("return_url")
    private String returnUrl;

    @JsonProperty("cancel_url")
    private String cancelUrl;
}
