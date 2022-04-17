package com.paypal.integration.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class SubscriptionCreationRequest {
    @JsonProperty("plan_id")
    private String planId;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("subscriber")
    private Subscriber subscriber;

    @JsonProperty("application_context")
    private ApplicationContext applicationContext;

    public static SubscriptionCreationRequest getRequest (String planId, Date startTime, String quantity, String email, String domain) {
        SubscriptionCreationRequest request = new SubscriptionCreationRequest();
        request.setPlanId(planId);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        request.setStartTime(formatter.format(startTime) + "Z");
        request.setQuantity(quantity);
        Subscriber subscriber = new Subscriber();
        subscriber.setEmailAddress(email);
        request.setSubscriber(subscriber);
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.setBrandName("PerformFlow");
        applicationContext.setUserAction("SUBSCRIBE_NOW");
        applicationContext.setReturnUrl(domain + "/subscription/callback");
        applicationContext.setCancelUrl(domain + "/subscription/cancel");
        request.setApplicationContext(applicationContext);
        return request;
    }
}
