package com.cs2340.goodwillhunting.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddItemActivity extends AppCompatActivity {
    private static final String TAG = "AddItemActivity";

    private Button btnAdd, btnCancel;
    private EditText mName, mDescription, mid, mkey, mqty;
    private String itemId;


    DatabaseReference reference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additemlayout);
        btnAdd = findViewById(R.id.buttonAdd);
        btnCancel = findViewById(R.id.btn_cancel);
        mDescription = findViewById(R.id.et_itemdescription);
        mid = findViewById(R.id.et_itemid);
        mkey = findViewById(R.id.et_itemkey);
        mName = findViewById(R.id.et_itemname);
        mqty = findViewById(R.id.et_itemqty);
        getIncomingIntent();

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
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName.setText("");
                mDescription.setText("");
                mid.setText("");
                mkey.setText("");
                mqty.setText("");
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString();
                String description = mDescription.getText().toString();
                String id = mid.getText().toString();
                int key = Integer.parseInt(mkey.getText().toString());
                int qty = Integer.parseInt(mqty.getText().toString());

                if (!name.equals("") && !description.equals("") && !id.equals("") && !(qty==0) && !(key==0)) {
                    Item item = new Item(name, description, id, qty, key);
                    reference.child("items").child(mkey.getText().toString()).child(id).setValue(item);
                    Toast.makeText(AddItemActivity.this, "Item was saved", Toast.LENGTH_SHORT).show();
                    mName.setText("");
                    mDescription.setText("");
                    mid.setText("");
                    mkey.setText("");
                    mqty.setText("");
                    finish();
                } else {
                    Toast.makeText(AddItemActivity.this, "Information was incomplete or invalid", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("location_name")) {
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            final String locKey = getIntent().getStringExtra("location_key");
        }
    }
}

