package com.cs2340.goodwillhunting.model;

public enum UserType {

    CONSUMER ("Consumer"),
    EMPLOYEE ("Employee"),
    MANAGER ("Manager"),
    ADMIN ("Admin");

    private final String userType;

    UserType(String userType) {
        this.userType = userType;
    }

    public String getType() { return userType; }

    public String toString() { return userType; }
}
