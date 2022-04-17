package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CycleExecution {
    @JsonProperty("tenure_type")
    private String tenure_type;

    @JsonProperty("sequence")
    private int sequence;

    @JsonProperty("cycles_completed")
    private int cyclesCompleted;

    @JsonProperty("cycles_remaining")
    private int cyclesRemaining;

    @JsonProperty("current_pricing_scheme_version")
    private int currentPricingSchemeVersion;

    @JsonProperty("total_cycles")
    private int totalCycles;
}
