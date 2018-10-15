package com.cs2340.goodwillhunting.controllers;
import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Location;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import java.io.InputStream;
import java.util.List;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


// Shows all the locations
public class MainMenuActivity extends Activity {

    private static final String TAG = "MainMenu";
    private Button logOut;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        logOut = findViewById(R.id.button_logout);

        reference = FirebaseDatabase.getInstance().getReference();

        InputStream inputStream = getResources().openRawResource(R.raw.locationdata);
        CSVFile csvFile = new CSVFile(inputStream);
        List locs = csvFile.read();

        for (int i = 1; i < locs.size(); i++) {
            String[] row = (String[]) locs.get(i);
            Location loc = new Location(row[0], row[1], row[2], row[3], row[4], row[5], row[6],
                    row[7], row[8], row[9], row[10]);
            reference.child("locations").child(loc.getKey()).setValue(loc);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, LoggedInUser.getInstance().getUserType().toString());
        Toast.makeText(MainMenuActivity.this, "This user is a "
                + LoggedInUser.getInstance().getUserType().toString(), Toast.LENGTH_LONG).show();


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, WelcomeScreenActivity.class);
                startActivity(intent);
            }
        });
    }


}
