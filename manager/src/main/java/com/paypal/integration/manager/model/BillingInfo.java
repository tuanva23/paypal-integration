package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BillingInfo {
    @JsonProperty("outstanding_balance")
    private OutstandingBalance outstandingBalance;

    @JsonProperty("cycle_executions")
    private List<OutstandingBalance> cycleExecutions;

    @JsonProperty("next_billing_time")
    private Date nextBillingTime;

    @JsonProperty("failed_payments_count")
    private int failedPaymentsCount;
}
