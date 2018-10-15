package com.cs2340.goodwillhunting.controllers;

import com.cs2340.goodwillhunting.model.UserType;


/* Creates an instance of the current user */
public class LoggedInUser {

    private static LoggedInUser instance = new LoggedInUser();
    private UserType userType;
    private String email;
    private String password;
    private String logInID;

    // private constructor
    private LoggedInUser(){}

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

    /*********************
        GETTERS AND SETTERS
     */
    public UserType getUserType() { return userType; }
    public void setUserType(UserType type) { userType = type; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getLogInID() { return logInID; }
    public void setLogInID(String logInID) { this.logInID = logInID; }




}
