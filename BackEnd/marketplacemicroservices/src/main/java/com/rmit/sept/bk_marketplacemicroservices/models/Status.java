package com.rmit.sept.bk_marketplacemicroservices.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    PENDING,
    FULFILLMENT,
    SHIPPED,
    COMPLETED,
    CANCELLED;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Status fromValue(@JsonProperty("status") String status) {
        return Status.valueOf(status);
    }
}