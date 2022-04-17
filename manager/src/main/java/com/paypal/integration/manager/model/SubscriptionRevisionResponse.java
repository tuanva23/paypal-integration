package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class SubscriptionRevisionResponse {
    @JsonProperty("plan_id")
    private String planId;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("plan_overridden")
    private Date planOverridden;

    @JsonProperty("links")
    private List<Link> links;
}
