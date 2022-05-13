package com.paypal.integration.manager.api;

import com.braintreegateway.*;
import com.paypal.api.payments.*;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.integration.manager.model.*;
import com.paypal.integration.manager.model.api.SubscriptionCreationApiRequest;
import com.paypal.integration.manager.model.api.SubscriptionCreationApiResponse;
import com.paypal.integration.manager.model.api.SubscriptionRevisionApiRequest;
import com.paypal.integration.manager.model.api.SubscriptionRevisionApiResponse;
import com.paypal.integration.manager.service.GCPFirestoreService;
import com.paypal.integration.manager.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("subscription")
public class SubscriptionController {

    @Value("${domain}")
    private String domain;

    @Autowired
    private GCPFirestoreService firestoreService;

    @Autowired
    private SubscriptionService subscriptionService;

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleRuntimeException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping("/create")
    public Object createSubscription (@RequestBody SubscriptionCreationApiRequest apiRequest) {
        SubscriptionCreationRequest request = SubscriptionCreationRequest.getRequest(
                apiRequest.getPlanId(), new Date(), apiRequest.getQuantity(), apiRequest.getEmail(), domain
        );
        SubscriptionCreationResponse response = subscriptionService.createSubscription(request);
        return new SubscriptionCreationApiResponse() {{ setRedirectUrl(response.getLinks().get(0).getHref()); }};
    }

    @PostMapping("/revise")
    public Object reviseSubscription (@RequestBody SubscriptionRevisionApiRequest apiRequest) {
        SubscriptionRevisionRequest request = SubscriptionRevisionRequest.getRequest(
                apiRequest.getPlanId(), new Date(), apiRequest.getQuantity(), apiRequest.getEmail(), domain
        );
        SubscriptionRevisionResponse response = subscriptionService.reviseSubscription(apiRequest.getSubscriptionId(), request);
        return new SubscriptionRevisionApiResponse() {{ setRedirectUrl(response.getLinks().get(0).getHref()); }};
    }

    @GetMapping("/detail/{subscription-id}")
    public Object reviseSubscription (@PathVariable(value = "subscription-id") String subscriptionId) {
        return subscriptionService.getSubscriptionDetail(subscriptionId);
    }

    @GetMapping("/callback")
    public Object callback (
            @RequestParam(value = "subscription_id") String subscriptionId,
            @RequestParam(value = "ba_token") String baToken,
            @RequestParam(value = "token") String token) {
        return new RedirectView("");
    }

    @GetMapping("/cancel")
    public Object cancel (
            @RequestParam(value = "subscription_id") String subscriptionId,
            @RequestParam(value = "ba_token") String baToken,
            @RequestParam(value = "token") String token) {
        return new RedirectView("");
    }
}
