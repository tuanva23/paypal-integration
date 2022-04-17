package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SubscriptionCreationResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("id")
    private String id;

    @JsonProperty("create_time")
    private Date createTime;

    @JsonProperty("links")
    private List<Link> links;
}
