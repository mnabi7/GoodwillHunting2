package com.cs2340.goodwillhunting.controllers;

import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.cs2340.goodwillhunting.R;


public class LogInActivity extends Activity {

    private static final String TAG = "LogIn";

    // View
    private EditText emailField;
    private EditText passwordField;

    // Buttons
    private Button submit;

    // Firebase
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emailField = findViewById(R.id.et_email);
        passwordField = findViewById(R.id.et_password);
        submit = findViewById(R.id.button_submit);

        mAuth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = validateEmpty(emailField.getText().toString(), passwordField.getText().toString());

                if (valid) {
                    signIn();
                }
            }
        });
    }

    /**
     * sign in with email and password
     */
    private void signIn() {
        mAuth.signInWithEmailAndPassword(emailField.getText().toString(), passwordField.getText().toString())
                .addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with signed in user info
                            Log.d(TAG, "signIn:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(LogInActivity.this, "Sign in successful!", Toast.LENGTH_LONG).show();

                            // UPDATE UI
                            Intent intent = new Intent(LogInActivity.this, MainMenuActivity.class);
                            startActivity(intent);

                        } else {
                            // Sign in unsuccessful
                            Log.w(TAG, "signIn:failure");
                            Log.w(TAG, task.getException().getMessage());
                            Toast.makeText(LogInActivity.this, "Sign in failed", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    private boolean validateEmpty(String email, String password) {
        if (email.isEmpty()) {
            Toast.makeText(LogInActivity.this, "Please enter an email", Toast.LENGTH_LONG).show();
            return false;
        }
        if (password.isEmpty()) {
            Toast.makeText(LogInActivity.this, "Please enter a password", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }

}
