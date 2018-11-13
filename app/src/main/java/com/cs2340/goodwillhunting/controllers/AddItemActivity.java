package com.cs2340.goodwillhunting.controllers;
import com.cs2340.goodwillhunting.model.Location;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;
import android.content.Intent;


import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Item;
import com.cs2340.goodwillhunting.model.ItemsAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Class that organizes the User Interface
 */
public class AddItemActivity extends AppCompatActivity {
    private static final String TAG = "AddItemActivity";

    private Button btnAdd, btnCancel;
    private EditText mName, mDescription, mqty, mValue, mItemKey;
    private Spinner chooseCategory;
    private String itemId;


    DatabaseReference reference;

    /**
     * initializes the screen
     *
     * @param savedInstanceState a saved instance state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additemlayout);
        btnAdd = findViewById(R.id.buttonAdd);
        btnCancel = findViewById(R.id.btn_cancel);
        mDescription = findViewById(R.id.et_itemdescription);
        mValue = findViewById(R.id.et_itemvalue);
        mName = findViewById(R.id.et_itemname);
        mqty = findViewById(R.id.et_itemqty);
        mItemKey = findViewById(R.id.et_itemkey);
        chooseCategory = findViewById(R.id.category_spinner);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String locKey = extras.getString("location_key");
        final String locName = extras.getString("location_name");

        reference = FirebaseDatabase.getInstance().getReference();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "failed to read value");

            }
        });

        String catArr[] = new String[]{"Clothing", "Kitchen", "Electronics", "Household"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddItemActivity.this, android.R.layout.simple_spinner_item, catArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseCategory.setAdapter(adapter);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName.setText("");
                mDescription.setText("");
                mValue.setText("");
                mqty.setText("");
                mItemKey.setText("");
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString();
                String description = mDescription.getText().toString();
                String itemKey = mItemKey.getText().toString();
                double val = Double.parseDouble(mValue.getText().toString());
                int qty = Integer.parseInt(mqty.getText().toString());


                if (!name.equals("") && !description.equals("") && !(val==0) && !(qty==0)) {
                    final Item item = new Item(name, description, chooseCategory.getSelectedItem().toString(), qty, Integer.parseInt(locKey), val, locName, Integer.parseInt(itemKey));
                    reference.child("items").child("Location " + locKey).child("Item " + itemKey).setValue(item);
                    Toast.makeText(AddItemActivity.this, "Item was saved", Toast.LENGTH_SHORT).show();
                    mName.setText("");
                    mDescription.setText("");
                    mValue.setText("");
                    mqty.setText("");

                    reference.child("locations").child(locKey).child("items").push().setValue(item);

                    /*final DatabaseReference locRef = FirebaseDatabase.getInstance().getReference().child("locations");
                    locRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Location loc = snapshot.getValue(Location.class);

                                if (loc.getKey() == Integer.parseInt(locKey)) {
                                    Log.d(TAG, loc.toString());
                                    Log.d(TAG, item.toString());
                                    loc.addItem(item);
                                    locRef.child(Integer.toString(loc.getKey())).setValue(loc);
                                    break;
                                }
                            }



                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });*/
                    finish();
                } else {
                    Toast.makeText(AddItemActivity.this, "Information was incomplete or invalid", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

}

