package com.cs2340.goodwillhunting.controllers;
//
//import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Item;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.app.ActionBar;
//import android.view.MenuItem;
//
//
///**
// * An activity representing a single Location detail screen. This
// * activity is only used narrow width devices.
// * in a {@link LocationListActivity}.
// *
// * Here we need to display a list of location info.  Our view will be pretty similar
// * since we are displaying a list of location info  We are going to use a
// * recycler view again.
// */
//public class LocationDetailActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_location_detail);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
//        //setSupportActionBar(toolbar);
//
//
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//
//
//        if (savedInstanceState == null) {
//            Bundle arguments = new Bundle();
//            arguments.putInt(LocationDetailFragment.ARG_LOCATION_KEY,
//                    getIntent().getIntExtra(LocationDetailFragment.ARG_LOCATION_KEY, 0));
//
//            LocationDetailFragment fragment = new LocationDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction().add(R.id.location_detail_container, fragment).commit();
//        }
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            // This ID represents the Home or Up button. In the case of this
//            // activity, the Up button is shown.
//            navigateUpTo(new Intent(this, LocationListActivity.class));
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//
//    }
//
//}

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Location;
import com.cs2340.goodwillhunting.model.ItemsAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LocationDetailActivity extends AppCompatActivity {
    private static final String TAG = "LocationDetailActivity";
    FloatingActionButton info;
    FloatingActionButton addItem;
    private DatabaseReference reference;
    ArrayList<Location> list;
    ArrayList<Item> itemList;
    ItemsAdapter adapter;
    String extraName="";
    String extraKey="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        Log.d(TAG, "onCreate: started");
        getIncomingIntent();
        info = (FloatingActionButton) findViewById(R.id.fab);


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PopActivity.class);
                intent.putExtra("location_name", extraName);
                startActivity(intent);
            }
        });

        addItem = (FloatingActionButton) findViewById(R.id.addItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                intent.putExtra("location_key",extraKey);
                startActivity(intent);
            }
        });

    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("location_name")) {

            Log.d(TAG, "getIncomingIntent: found intent extras.");
            final String locName = getIntent().getStringExtra("location_name");
            extraName = locName;

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("locations");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<Location> list = new ArrayList<Location>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Location l = dataSnapshot1.getValue(Location.class);
                        if (l.getName().equals(locName)) {
                            list.add(l);
                        }
                    }
                    extraKey = "" + list.get(0).getKey();

                    setStoreName(locName);


                    final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.item_List);
                    DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("items");

                    //should be holding the location items # container of items
                    reference2 = reference2.child(extraKey);
                    recyclerView.setLayoutManager(new LinearLayoutManager(LocationDetailActivity.this));
                    reference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.d(TAG, "reached items for location #");
                            ArrayList<Item> itemList = new ArrayList<Item>();
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Item it = dataSnapshot1.getValue(Item.class);
                                itemList.add(it);
                            }
                            adapter = new ItemsAdapter(LocationDetailActivity.this, itemList);
                            recyclerView.setAdapter(adapter);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(LocationDetailActivity.this, "Idk wtf i am doing, i just hope this works pt. 2", Toast.LENGTH_SHORT).show();

                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }
    private void setStoreName(String storeName) {
        TextView name = findViewById(R.id.store_Name);
        name.setText(storeName);
    }
}
