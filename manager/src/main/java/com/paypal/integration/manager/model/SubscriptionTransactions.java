package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SubscriptionTransactions {
    @JsonProperty("transactions")
    private List<Transaction> transactions;

    @JsonProperty("links")
    private List<Link> links;
}
