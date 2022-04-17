package com.paypal.integration.manager.model;

import lombok.Data;

@Data
public class Link {
    private String href;
    private String rel;
    private String method;
}
