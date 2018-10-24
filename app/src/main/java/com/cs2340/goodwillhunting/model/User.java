package com.cs2340.goodwillhunting.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class User implements Parcelable {
    /** allow us to assign unique id numbers to each user */
    //private static int Next_Id = 0;

    /** this user id number */
    //private int _id;

    /** this user email */
    private String _email;

    /** this user password */
    private String _password;

    /** this user's type */
    private UserType _user_type;

    private DatabaseReference reference;


    /**
     * No param constructor -- DO NOT CALL NORMALLY
     * This constructor only for GUI use in edit/new student dialog
     */
    public User() {
        this("enter new email" , "NA", UserType.CONSUMER);
    }

    /**
     * Make a new user
     * @param email      the user's name
     * @param password   the user's major
     */
    public User(String email, String password, UserType type) {

        _email = email;
        _password = password;
        _user_type = type;


    }


    /* **********************
     * Getters and setters
     */

    //no setter for this.  id is a read only field
    //public int getId() { return _id; }

    /** Returns user email*/
    public String getEmail() { return _email; }
    public void setName(String email) { _email = email; }

    /** Returns user password*/
    public String getPassword() {return _password; }
    public void setPassword(String password) { _password = password; }

    // no setter for this. type is a read only field
    public UserType getType() { return _user_type; }


    /**
     * Constructor used by Parcel to make a new user out of the
     * parceled information
     *
     * @param in  the parcel containing the user information
     */
    private User(Parcel in) {
        _email = in.readString();
        _password = in.readString();
        //_id = in.readInt();
    }
    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_email);
        dest.writeString(_password);
        //dest.writeInt(_id);
    }
    /**
     * Should not have to edit this method if the constructor and write method are
     * working correctly.
     */
    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };






}
