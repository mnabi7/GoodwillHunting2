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
import android.widget.EditText;
import android.content.Intent;

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
    private RadioGroup radioGroup1;

    private RadioGroup radioGroup2;
    private Spinner chooseCategory;
    private EditText itemName;
    private Button submit;
    private DatabaseReference reference;
    ArrayList<String> locNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        chooseLoc = findViewById(R.id.loc_spinner);
        chooseCategory = findViewById(R.id.cat_spinner);
        itemName = findViewById(R.id.et_item_name);
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

        String catArr[] = new String[]{"Clothing", "Kitchen", "Electronics", "Household"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(SearchActivity.this, android.R.layout.simple_spinner_item, catArr);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseCategory.setAdapter(adapter1);


    }

    @Override
    protected void onStart() {
        super.onStart();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroup1 = (RadioGroup) findViewById(R.id.radio_group1);
                radioGroup2 = (RadioGroup) findViewById(R.id.radio_group2);
                RadioButton loc_answer = ((RadioButton) findViewById(radioGroup1.getCheckedRadioButtonId()));
                RadioButton item_answer = ((RadioButton) findViewById((radioGroup2.getCheckedRadioButtonId())));

                if (loc_answer == null) {
                    Toast.makeText(SearchActivity.this, "Please select a first search option", Toast.LENGTH_LONG).show();
                } else {
                    if (loc_answer.getId() == R.id.radio_all_loc) {
                        if (item_answer == null) {
                            Toast.makeText(SearchActivity.this, "Please select a second search option", Toast.LENGTH_LONG).show();
                        } else {
                            if (item_answer.getId() == R.id.radio_item_name) {
                                if (itemName.getText().toString().isEmpty()) {
                                    Toast.makeText(SearchActivity.this, "No item entered", Toast.LENGTH_LONG).show();
                                } else {
                                    //Toast.makeText(SearchActivity.this, "All location and item name", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(SearchActivity.this, SearchLocationResultActivity.class);
                                    Bundle extras = new Bundle();
                                    extras.putString("item_name", itemName.getText().toString());
                                    extras.putString("category", null);
                                    extras.putString("single_loc", null);
                                    intent.putExtras(extras);
                                    startActivity(intent);
                                }
                            } else if (item_answer.getId() == R.id.radio_categories) {
                                //Toast.makeText(SearchActivity.this, "All locations and category", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SearchActivity.this, SearchLocationResultActivity.class);
                                Bundle extras = new Bundle();
                                extras.putString("category", chooseCategory.getSelectedItem().toString());
                                extras.putString("item_name", null);
                                extras.putString("single_loc", null);
                                intent.putExtras(extras);
                                startActivity(intent);
                            }
                        }
                    } else if (loc_answer.getId() == R.id.radio_single_loc) {
                        if (item_answer == null) {
                            Toast.makeText(SearchActivity.this, "Please select a second search option", Toast.LENGTH_LONG).show();
                        } else {
                            if (item_answer.getId() == R.id.radio_item_name) {
                                if (itemName.getText().toString().isEmpty()) {
                                    Toast.makeText(SearchActivity.this, "No item entered", Toast.LENGTH_LONG).show();
                                } else {
                                    //Toast.makeText(SearchActivity.this, "Single location and item name", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(SearchActivity.this, SearchLocationResultActivity.class);
                                    Bundle extras = new Bundle();
                                    extras.putString("category", null);
                                    extras.putString("item_name", itemName.getText().toString());
                                    extras.putString("single_loc", chooseLoc.getSelectedItem().toString());
                                    intent.putExtras(extras);
                                    startActivity(intent);
                                }
                            } else if (item_answer.getId() == R.id.radio_categories) {
                                //Toast.makeText(SearchActivity.this, "Single locations and category", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SearchActivity.this, SearchLocationResultActivity.class);
                                Bundle extras = new Bundle();
                                extras.putString("category", chooseCategory.getSelectedItem().toString());
                                extras.putString("item_name", null);
                                extras.putString("single_loc", chooseLoc.getSelectedItem().toString());
                                intent.putExtras(extras);
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        });

    }

    // needed method for radio buttons, but doesn't do anything
    public void onRadioButtonClicked(View view) {}

}
