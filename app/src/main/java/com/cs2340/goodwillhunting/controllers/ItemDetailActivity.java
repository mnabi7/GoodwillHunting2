package com.cs2340.goodwillhunting.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ItemDetailActivity extends Activity {

    Button close;
    private static final String TAG = "ItemDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        getIncomingIntent();
        close = (Button) findViewById(R.id.close_details);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if(getIntent().hasExtra("item_name")&&getIntent().hasExtra("item_key")) {

            Log.d(TAG, "getIncomingIntent: found intent extras.");
            final String itemName = getIntent().getStringExtra("item_name");
            final String itemKey = getIntent().getStringExtra("item_key");
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("items").child(itemKey);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<Item> list = new ArrayList<Item>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                        Item it = dataSnapshot1.getValue(Item.class);
                        if (it.getname().equals(itemName)) {
                            list.add(it);
                        }
                    }
                    final String itemToString = list.get(0).toString();
                    System.out.println(" alsd;kj  heeeeeyyyyyyyyyy           " + itemToString);

                    setDescription(itemToString, itemName);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }
    private void setDescription(String itemDescription, String itemName) {
        Log.d(TAG, "setName: setting the store name to widgets.");
        TextView desc = findViewById(R.id.item_description);
        TextView name = findViewById(R.id.item_name);

        desc.setText(itemDescription);
        name.setText(itemName);

    }
}
