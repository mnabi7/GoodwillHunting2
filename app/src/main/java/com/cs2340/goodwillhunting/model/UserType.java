package com.cs2340.goodwillhunting.model;

/**
 * enum of different user types
 */
public enum UserType {

    CONSUMER ("Consumer"),
    EMPLOYEE ("Employee"),
    MANAGER ("Manager"),
    ADMIN ("Admin");

    private final String userType;

    /**
     * method to set a user type
     * @param userType usertype to be set
     */
    UserType(String userType) {
        this.userType = userType;
    }

    /**
     * getter for type
     * @return user type
     */
    public String getType() { return userType; }

    /**
     * to string method to return user type
     * @return user type
     */
    public String toString() { return userType; }
}
