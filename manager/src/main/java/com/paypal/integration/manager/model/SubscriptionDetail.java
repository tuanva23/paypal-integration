package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SubscriptionDetail {
    @JsonProperty("status")
    private String status;

    @JsonProperty("status_update_time")
    private Date statusUpdateTime;

    @JsonProperty("id")
    private String id;

    @JsonProperty("plan_id")
    private String plan_id;

    @JsonProperty("start_time")
    private Date start_time;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("subscriber")
    private Subscriber subscriber;

    @JsonProperty("billing_info")
    private BillingInfo billingInfo;

    @JsonProperty("auto_renewal")
    private boolean autoRenewal;

    @JsonProperty("create_time")
    private Date createTime;

    @JsonProperty("update_time")
    private Date updateTime;

    @JsonProperty("plan_overridden")
    private boolean planOverridden;

    @JsonProperty("links")
    private List<Link> links;
}
