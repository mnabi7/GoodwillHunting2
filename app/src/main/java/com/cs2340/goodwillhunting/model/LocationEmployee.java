package com.cs2340.goodwillhunting.model;

/**
 * Class to define a location employee extends the user class
 */
public class LocationEmployee extends User {

    /**
     * No-args constructor to invoke a location employee
     */
    public LocationEmployee() {
        super();
    }

    /**
     * Constructor to invoke a location employee
     * @param email user email
     * @param password user password
     */
    public LocationEmployee(String email, String password) {
        super(email, password, UserType.valueOf("EMPLOYEE"));
    }
}
