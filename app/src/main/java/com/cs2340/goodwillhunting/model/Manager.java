package com.cs2340.goodwillhunting.model;

public class Manager extends User {

    public Manager () {
        super();
    }

    public Manager(String email, String password) {
        super(email, password, UserType.valueOf("MANAGER"));
    }
}
