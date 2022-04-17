package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Transaction {
    @JsonProperty("status")
    private String status;

    @JsonProperty("id")
    private String id;

    @JsonProperty("amount_with_breakdown")
    private String amountWithBreakdown;

    @JsonProperty("payer_name")
    private Name name;

    @JsonProperty("payer_email")
    private String payerEmail;

    @JsonProperty("time")
    private String time;
}
