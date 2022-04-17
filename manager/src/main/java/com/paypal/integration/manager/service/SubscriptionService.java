package com.paypal.integration.manager.service;

import com.paypal.integration.manager.model.*;
import com.paypal.integration.manager.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.UUID;

@Service
public class SubscriptionService {
    @Autowired
    private RestTemplate restTemplate;

    // inject value from application.properties
    @Value("${paypal.url}")
    private String API_URL;

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public SubscriptionDetail getSubscriptionDetail (String subscriptionId)
    {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(API_URL)
                .path("v1/billing/subscriptions")
                .path("/" + subscriptionId);

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, requestEntity, SubscriptionDetail.class).getBody();
    }

    public SubscriptionTransactions getSubscriptionTransactions (String subscriptionId, Date startTime, Date endTime)
    {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(API_URL)
                .path("v1/billing/subscriptions")
                .path("/" + subscriptionId)
                .path("/transactions")
                .queryParam("start_time", DateTimeUtil.toString(startTime))
                .queryParam("end_time", DateTimeUtil.toString(endTime));

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, requestEntity, SubscriptionTransactions.class).getBody();
    }

    public SubscriptionCreationResponse createSubscription (SubscriptionCreationRequest request) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(API_URL)
                .path("v1/billing/subscriptions");

        HttpHeaders headers = new HttpHeaders();
        headers.set("PayPal-Request-Id", UUID.randomUUID().toString());
        headers.setBasicAuth(clientId, clientSecret);

        HttpEntity<SubscriptionCreationRequest> requestEntity = new HttpEntity<>(request, headers);

        return restTemplate.postForObject(uriBuilder.toUriString(), requestEntity, SubscriptionCreationResponse.class);
    }

    public SubscriptionRevisionResponse reviseSubscription (String subscriptionId, SubscriptionRevisionRequest request) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(API_URL)
                .path("v1/billing/subscriptions")
                .path("/" + subscriptionId)
                .path("/revise");

        HttpHeaders headers = new HttpHeaders();
        headers.set("PayPal-Request-Id", UUID.randomUUID().toString());
        headers.setBasicAuth(clientId, clientSecret);

        HttpEntity<SubscriptionRevisionRequest> requestEntity = new HttpEntity<>(request, headers);

        return restTemplate.postForObject(uriBuilder.toUriString(), requestEntity, SubscriptionRevisionResponse.class);
    }
}
