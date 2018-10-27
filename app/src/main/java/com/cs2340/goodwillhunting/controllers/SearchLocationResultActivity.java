package com.cs2340.goodwillhunting.controllers;
import com.cs2340.goodwillhunting.model.Item;
import com.cs2340.goodwillhunting.model.ItemsAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Location;
import com.cs2340.goodwillhunting.model.MyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class SearchLocationResultActivity extends Activity {

    private static final String TAG = "SearchLocationResult";
    private Button back;
    private DatabaseReference reference;
    private DatabaseReference reference2;
    RecyclerView recyclerView;
    ArrayList<Location> locationList;
    ArrayList<Item> itemList;
    MyAdapter adapter;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location_result);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String category = extras.getString("category");
        final String itemName = extras.getString("item_name"); //itemName will always be valid -- check is done in previous activity
        final String singleLoc = extras.getString("single_loc");

        back = findViewById(R.id.button_back);


        if (singleLoc == null && category == null && itemName != null) {
            //Log.d(TAG, "SEARCHING BY ALL LOCATIONS AND ITEM");
            DatabaseReference locRef = FirebaseDatabase.getInstance().getReference().child("locations");
            //DatabaseReference itemRef = FirebaseDatabase.getInstance().getReference().child("items");

            recyclerView = (RecyclerView) findViewById(R.id.myRecycler);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            locRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    locationList = new ArrayList<Location>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Location l = dataSnapshot1.getValue(Location.class);
                        //Log.d(TAG, l.getItems().get())
                        HashMap<String, Item> itemMap = l.getItems();
                        if (itemMap.isEmpty()) { Log.d(TAG, "NULL"); }
                        else{  Log.d(TAG, "then why tf isnt this working"); }


                        for (String key : itemMap.keySet()) {
                            Log.d(TAG, "KEY: " + key);
                            Item item = itemMap.get(key);
                            Log.d(TAG, item.getName() + " " + itemName);
                            if (item.getName().equals(itemName) && !locationList.contains(l)) {
                                Log.d(TAG, "FOUND");
                                locationList.add(l);
                            }
                        }
                    }
                    if (locationList.isEmpty()) {
                        Toast.makeText(SearchLocationResultActivity.this, "ITEM DOES NOT EXIST AT ANY LOCATION", Toast.LENGTH_LONG).show();
                    }
                    adapter = new MyAdapter(SearchLocationResultActivity.this, locationList, itemName, null);
                    recyclerView.setAdapter(adapter);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

        if (singleLoc == null && category != null && itemName == null) {
            //Log.d(TAG, "SEARCHING BY ALL LOCATIONS AND CATEGORY");
            DatabaseReference locRef = FirebaseDatabase.getInstance().getReference().child("locations");
            //DatabaseReference itemRef = FirebaseDatabase.getInstance().getReference().child("items");

            recyclerView = (RecyclerView) findViewById(R.id.myRecycler);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            locRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    locationList = new ArrayList<Location>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Location l = dataSnapshot1.getValue(Location.class);
                        //Log.d(TAG, l.getItems().get())
                        HashMap<String, Item> itemMap = l.getItems();
                        if (itemMap.isEmpty()) { Log.d(TAG, "NULL"); }
                        else{  Log.d(TAG, "then why tf isnt this working"); }

                        for (String key : itemMap.keySet()) {

                            Item item = itemMap.get(key);

                            if (item.getCategory().equals(category) && !locationList.contains(l)) {
                                Log.d(TAG, "FOUND");
                                locationList.add(l);
                            }
                        }
                    }
                    adapter = new MyAdapter(SearchLocationResultActivity.this, locationList, null, category);
                    recyclerView.setAdapter(adapter);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (singleLoc != null && category == null && itemName != null) {
            //Log.d(TAG, "SEARCHING BY SINGLE LOCATION AND ITEM");
            DatabaseReference locRef = FirebaseDatabase.getInstance().getReference().child("locations");
            //DatabaseReference itemRef = FirebaseDatabase.getInstance().getReference().child("items");

            recyclerView = (RecyclerView) findViewById(R.id.myRecycler);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            locRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    locationList = new ArrayList<Location>();
                    itemList = new ArrayList<Item>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Location l = dataSnapshot1.getValue(Location.class);
                        //Log.d(TAG, l.getItems().get())

                        Log.d(TAG, l.getName());
                        if (l.getName().equals(singleLoc)) {
                            Log.d(TAG, "found");
                            HashMap<String, Item> itemMap = l.getItems();
                            if (itemMap.isEmpty()) {
                                Log.d(TAG, "NULL");
                            } else {
                                Log.d(TAG, "then why tf isnt this working");
                            }

                            for (String key : itemMap.keySet()) {
                                Log.d(TAG, "KEY: " + key);
                                Item item = itemMap.get(key);
                                Log.d(TAG, item.getName() + " " + itemName);
                                if (item.getName().equals(itemName) && !locationList.contains(l)) {
                                    Log.d(TAG, "FOUND");
                                    itemList.add(item);
                                    locationList.add(l);
                                }
                            }
                        }
                    }
                    if (locationList.isEmpty()) {
                        Toast.makeText(SearchLocationResultActivity.this, "ITEM DOES NOT EXIST AT THIS LOCATION", Toast.LENGTH_LONG).show();
                    }
                    itemsAdapter = new ItemsAdapter(SearchLocationResultActivity.this, itemList);
                    recyclerView.setAdapter(itemsAdapter);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

        if (singleLoc != null && category != null && itemName == null) {
            //Log.d(TAG, "SEARCHING BY SINGLE LOCATION AND CATEGORY");
            DatabaseReference locRef = FirebaseDatabase.getInstance().getReference().child("locations");
            //DatabaseReference itemRef = FirebaseDatabase.getInstance().getReference().child("items");

            recyclerView = (RecyclerView) findViewById(R.id.myRecycler);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            locRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //locationList = new ArrayList<Location>();
                    itemList = new ArrayList<Item>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Location l = dataSnapshot1.getValue(Location.class);
                        //Log.d(TAG, l.getItems().get())

                        Log.d(TAG, l.getName());
                        if (l.getName().equals(singleLoc)) {
                            Log.d(TAG, "found");
                            HashMap<String, Item> itemMap = l.getItems();
                            if (itemMap.isEmpty()) {
                                Log.d(TAG, "NULL");
                            } else {
                                Log.d(TAG, "then why tf isnt this working");
                            }

                            for (String key : itemMap.keySet()) {

                                Item item = itemMap.get(key);
                                if (item.getCategory().equals(category) && !itemList.contains(item)) {
                                    itemList.add(item);
                                }
                            }
                        }
                    }
                    if (itemList.isEmpty()) {
                        Toast.makeText(SearchLocationResultActivity.this, "NO ITEMS IN THIS CATEGORY AT THIS LOCATION", Toast.LENGTH_LONG).show();
                    }
                    itemsAdapter = new ItemsAdapter(SearchLocationResultActivity.this, itemList);
                    recyclerView.setAdapter(itemsAdapter);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }




    }

    @Override
    protected void onStart() {
        super.onStart();

        /*DatabaseReference userRef = reference.child("users");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userType = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        userRef.addListenerForSingleValueEvent(valueEventListener);*/


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchLocationResultActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });




    }
}
