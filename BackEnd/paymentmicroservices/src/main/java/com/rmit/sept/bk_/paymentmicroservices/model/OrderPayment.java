package com.rmit.sept.bk_.paymentmicroservices.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderPayment {
    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;

}
