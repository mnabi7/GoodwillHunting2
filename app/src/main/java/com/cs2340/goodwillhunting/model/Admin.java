package com.cs2340.goodwillhunting.model;

public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(String email, String password) {
        super(email, password, UserType.valueOf("ADMIN"));
    }
}
