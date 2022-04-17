package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AmountWithBreakdown {
    @JsonProperty("gross_amount")
    private Amount grossAmount;

    @JsonProperty("fee_amount")
    private Amount feeAmount;

    @JsonProperty("net_amount")
    private Amount netAmount;
}
