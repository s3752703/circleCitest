package com.rmit.sept.bk_loginservices.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserType {
    PUBLIC_USER,
    ADMIN,
    SHOP_KEEPER,
    PUBLISHER;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static UserType fromValue(@JsonProperty("UserType") String User_type) {
        return UserType.valueOf(User_type);
    }

}
