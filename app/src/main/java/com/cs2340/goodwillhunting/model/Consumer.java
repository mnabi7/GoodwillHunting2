package com.cs2340.goodwillhunting.model;

/**
 * Class to define a consumer extends the User class
 */
public class Consumer extends User {

    /**
     * No-args constructor to invoke a consumer
     */
    public Consumer() {
        super();
    }

    /**
     * Constructor to invoke a consumer
     * @param email user email
     * @param password user password
     */
    public Consumer(String email, String password) {
        super(email, password, UserType.valueOf("CONSUMER"));
    }
}
