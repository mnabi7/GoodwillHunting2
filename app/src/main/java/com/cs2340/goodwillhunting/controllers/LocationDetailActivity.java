package com.cs2340.goodwillhunting.controllers;
//
//import com.cs2340.goodwillhunting.R;
//import com.cs2340.goodwillhunting.model.Location;
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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Location;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LocationDetailActivity extends AppCompatActivity {
    private static final String TAG = "LocationDetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        Log.d(TAG, "onCreate: started");
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if(getIntent().hasExtra("location_name")) {

            Log.d(TAG, "getIncomingIntent: found intent extras.");
            final String locName = getIntent().getStringExtra("location_name");

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("locations");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<Location> list = new ArrayList<Location>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                        Location l = dataSnapshot1.getValue(Location.class);
                        if (l.getName().equals(locName)) {
                            list.add(l);
                        }
                    }
                    final String locAddress = list.get(0).getAddress();
                    final String locCity = list.get(0).getCity();
                    final String locKey = Integer.toString(list.get(0).getKey());
                    final String locLatitude = list.get(0).getLatitude();
                    final String locLongitude = list.get(0).getLongitude();
                    final String locPhone = list.get(0).getPhone();
                    final String locState = list.get(0).getState();
                    final String locType = list.get(0).getType();
                    final String locWebsite = list.get(0).getWebsite();
                    final String locZip = list.get(0).getZip();

                    setStoreName(locName, locAddress, locCity, locKey, locLatitude, locLongitude, locPhone,locState,locType,locWebsite,locZip);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }
    private void setStoreName(String storeName, String address, String city, String key, String latitude,String longitude,
                              String phone, String state, String type, String  website, String zip) {
        Log.d(TAG, "setName: setting the store name to widgets.");
        TextView name = findViewById(R.id.store_Name);
        TextView add = findViewById(R.id.store_address);
        TextView c = findViewById(R.id.store_city);
        TextView k = findViewById(R.id.store_key);
        TextView l = findViewById(R.id.store_latitude);
        TextView lon = findViewById(R.id.store_longitude);
        TextView p = findViewById(R.id.store_phone);
        TextView st = findViewById(R.id.store_state);
        TextView t = findViewById(R.id.store_type);
        TextView w = findViewById(R.id.store_website);
        TextView z = findViewById(R.id.store_zip);

        name.setText(storeName);
        add.setText(address);
        c.setText(city);
        k.setText(key);
        l.setText(latitude);
        lon.setText(longitude);
        p.setText(phone);
        st.setText(state);
        t.setText(type);
        w.setText(website);
        z.setText(zip);


    }

}
