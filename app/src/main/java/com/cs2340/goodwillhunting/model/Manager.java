package com.cs2340.goodwillhunting.model;

/**
 * Class to define a manager extends user
 */
public class Manager extends User {

    /**
     * No-args constructor to invoke manager
     */
    public Manager () {
        super();
    }

    /**
     * Constructor to invoke manager
     * @param email user email
     * @param password user password
     */
    public Manager(String email, String password) {
        super(email, password, UserType.valueOf("MANAGER"));
    }
}
