package com.cs2340.goodwillhunting.controllers;

import com.cs2340.goodwillhunting.model.UserType;


/**
 * class that creates an instance of a user
 */
public class LoggedInUser {

    private static LoggedInUser instance = new LoggedInUser();
    private UserType userType;
    private String email;
    private String password;
    private String logInID;

    /**
     * preivate constructor
     */
    private LoggedInUser(){}

    /**
     * returns an instance of this
     */
    public static LoggedInUser getInstance() { return instance; }

    /**
     * initialize the logged in user's info
     * @param email
     * @param password
     * @param logInID
     */
    public void init(String email, String password, String logInID) {
        this.email = email;
        this.password = password;
        this.logInID = logInID;
    }

    /**
     * gets the user type
     */
    public UserType getUserType() { return userType; }

    /**
     * sets the user type
     */
    public void setUserType(UserType type) { userType = type; }

    /**
     * gets the email
     */
    public String getEmail() { return email; }

    /**
     * sets the email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * gets the password
     */
    public String getPassword() { return password; }

    /**
     * sets the user password
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * gets the user login ID
     */
    public String getLogInID() { return logInID; }

    /**
     * sets the user login ID
     */
    public void setLogInID(String logInID) { this.logInID = logInID; }




}
