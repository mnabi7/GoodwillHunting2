package com.cs2340.goodwillhunting.controllers;
import com.cs2340.goodwillhunting.R;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Spinner;
import android.util.Log;

import android.content.Intent;

import com.cs2340.goodwillhunting.model.User;
import com.cs2340.goodwillhunting.model.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;





public class SignUpActivity extends Activity {

    private static final String TAG = "SignUp";

    // View
    private EditText logInIDField;
    private EditText emailField;
    private EditText passwordField;
    private EditText confirmField;

    // Buttons
    private Button createAcct;
    private Button cancel;

    // Spinner
    private Spinner userType;

    //Checkbox
    private CheckBox checkbox;

    // Firebase
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // View
        //logInIDField = findViewById(R.id.et_login);
        emailField = findViewById(R.id.et_email);
        passwordField = findViewById(R.id.et_password);
        confirmField = findViewById(R.id.et_rep_pass);

        // Buttons
        createAcct = findViewById(R.id.button_submit);
        cancel = findViewById(R.id.button_cancel);

        // Spinner
        userType = findViewById(R.id.spinner);

        //Checkbox
        checkbox = findViewById(R.id.checkbox_show);

        // Firebase
        mAuth = FirebaseAuth.getInstance();

        /*
            Set up the adapter to show the allowable user types
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();

        createAcct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean valid = validate(emailField.getText().toString(), passwordField.getText().toString(),
                            confirmField.getText().toString());
                if (valid) {
                    createAccount();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               Intent intent = new Intent(SignUpActivity.this, WelcomeScreenActivity.class);
               startActivity(intent);
           }
        });
        /*
            Hides or shows password when checkbox is checked
         */

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // show password
                    passwordField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirmField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password
                    passwordField.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    confirmField.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    private void createAccount() {
        mAuth.createUserWithEmailAndPassword(emailField.getText().toString(), passwordField.getText().toString())
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI to show user info
                            Log.d(TAG, "createAccount:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignUpActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
                            Log.d(TAG, userType.getSelectedItem().toString());
                            LoggedInUser.getInstance().setUserType(UserType.valueOf(userType.getSelectedItem().toString().toUpperCase()));
                            LoggedInUser.getInstance().init(emailField.getText().toString(), passwordField.getText().toString(),
                                                                "DUMMY");
                            // UPDATE UI
                            Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display message to user
                            Log.w(TAG, "createAccount:failure");
                            Log.w(TAG, task.getException().getMessage());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                            if (passwordField.getText().toString().length() < 6) {
                                Toast.makeText(SignUpActivity.this, "Password must be at least 6 characters", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }


    /**
     * check to see if password and confirm password match each other
     * @param password the password for the user
     * @param confirm the second password entered to compare
     * @return whether they match or not
     */
    private boolean validate(String email, String password, String confirm) {
        if(password.equals(confirm) && email.contains("@")) {
            return true;
        } else {

            if (!email.contains("@") || email.isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Invalid email!", Toast.LENGTH_LONG).show();
            }
            if (password.isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Invalid password!", Toast.LENGTH_LONG).show();
            }
            if (!password.equals(confirm)) {
                Toast.makeText(SignUpActivity.this, "Password does not match", Toast.LENGTH_LONG).show();
            }
            return false;
        }


    }

}
