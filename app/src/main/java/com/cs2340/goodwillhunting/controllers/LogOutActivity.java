package com.cs2340.goodwillhunting.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cs2340.goodwillhunting.R;

public class LogOutActivity extends Activity {
private Button mLogoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);

        mLogoutButton = (Button) findViewById(R.id.button_logout);

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogOutActivity.this, WelcomeScreenActivity.class);
                startActivity(intent);
            }
        });
    }


}
