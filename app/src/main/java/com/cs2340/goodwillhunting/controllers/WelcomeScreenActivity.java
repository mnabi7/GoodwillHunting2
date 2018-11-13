package com.cs2340.goodwillhunting.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.common.GoogleApiAvailability;
import com.cs2340.goodwillhunting.R;
import com.google.firebase.FirebaseApp;

/**
 * class that controls the welcome screen
 */
public class WelcomeScreenActivity extends Activity {
    final static String TAG = "WelcomeScreenActivity";
    private Button mSignUpButton;
    private Button mLoginButton;

    /**
     * sets up the welcome screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        mLoginButton = (Button) findViewById(R.id.button_login);
        mSignUpButton = (Button) findViewById(R.id.button_signup);
        FirebaseApp.initializeApp(this);
        showGooglePlayServicesStatus();
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeScreenActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeScreenActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * method that shows the google play services status
     */
    private void showGooglePlayServicesStatus() {
        GoogleApiAvailability apiAvail = GoogleApiAvailability.getInstance();
        int errorCode = apiAvail.isGooglePlayServicesAvailable(this);
        String msg = "Play Services: " + apiAvail.getErrorString(errorCode);
        Log.d(TAG, msg);
    }

}
