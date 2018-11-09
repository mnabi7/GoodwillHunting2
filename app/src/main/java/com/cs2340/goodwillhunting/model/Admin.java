package com.cs2340.goodwillhunting.model;

/**
 * Class to define an admin extends the User class
 */

public class Admin extends User {

    /**
     * No-args constructor to invoke an admin
     */
    public Admin() {
        super();
    }

    /**
     * Constructor to make new admin
     * @param email admin email
     * @param password admin password
     */
    public Admin(String email, String password) {
        super(email, password, UserType.valueOf("ADMIN"));
    }
}
