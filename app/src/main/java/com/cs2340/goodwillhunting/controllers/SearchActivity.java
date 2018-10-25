package com.cs2340.goodwillhunting.controllers;
import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Location;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

// search by single location or all location
// search by category or item name
public class SearchActivity extends Activity {

    private static final String TAG = "SearchActivity";
    private Spinner chooseLoc;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RadioButton allLocations;
    private RadioButton singleLocation;
    private Button submit;
    private DatabaseReference reference;
    ArrayList<String> locNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        allLocations = findViewById(R.id.radio_all_loc);
        singleLocation = findViewById(R.id.radio_single_loc);
        chooseLoc = findViewById(R.id.loc_spinner);
        submit = findViewById(R.id.button_submit_search);

        reference = FirebaseDatabase.getInstance().getReference().child("locations");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                locNames = new ArrayList<>();
                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    Location loc = snapShot.getValue(Location.class);
                    locNames.add(loc.getName());
                }
                String locArr[] = new String[locNames.size()];
                for (int i = 0; i < locArr.length; i++) {
                    locArr[i] = locNames.get(i);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter(SearchActivity.this, android.R.layout.simple_spinner_item, locArr);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                chooseLoc.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroup = (RadioGroup) findViewById(R.id.radio_group);
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_all_loc:
                Toast.makeText(SearchActivity.this, "ALL LOCATIONS", Toast.LENGTH_LONG).show();
                break;
            case R.id.radio_single_loc:
                Toast.makeText(SearchActivity.this, "SINGLE LOCATION", Toast.LENGTH_LONG).show();
                break;

        }
    }
}
