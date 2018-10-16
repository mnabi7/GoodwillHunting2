package com.cs2340.goodwillhunting.model;

public class Consumer extends User {

    public Consumer() {
        super();
    }

    public Consumer(String email, String password) {
        super(email, password, UserType.valueOf("CONSUMER"));
    }
}
