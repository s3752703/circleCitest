package com.rmit.sept.bk_marketplacemicroservices.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Condition {
    NEW,
    USED;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Condition fromValue(@JsonProperty("condition") String condition) {
        return Condition.valueOf(condition);
    }

}