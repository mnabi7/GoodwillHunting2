package com.cs2340.goodwillhunting.model;

public class LocationEmployee extends User {

    public LocationEmployee() {
        super();
    }

    public LocationEmployee(String email, String password) {
        super(email, password, UserType.valueOf("EMPLOYEE"));
    }
}
