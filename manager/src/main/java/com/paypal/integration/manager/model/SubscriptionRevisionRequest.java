package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class SubscriptionRevisionRequest {
    @JsonProperty("plan_id")
    private String planId;

    @JsonProperty("effective_time")
    private String effectiveTime;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("application_context")
    private ApplicationContext applicationContext;

    public static SubscriptionRevisionRequest getRequest (String planId, Date effectiveTime, String quantity, String email, String domain) {
        SubscriptionRevisionRequest request = new SubscriptionRevisionRequest();
        request.setPlanId(planId);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        request.setEffectiveTime(formatter.format(effectiveTime) + "Z");
        request.setQuantity(quantity);
        Subscriber subscriber = new Subscriber();
        subscriber.setEmailAddress(email);
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.setBrandName("PerformFlow");
        applicationContext.setUserAction("SUBSCRIBE_NOW");
        applicationContext.setReturnUrl(domain + "/subscription/callback");
        applicationContext.setCancelUrl(domain + "/subscription/cancel");
        request.setApplicationContext(applicationContext);
        return request;
    }
}
