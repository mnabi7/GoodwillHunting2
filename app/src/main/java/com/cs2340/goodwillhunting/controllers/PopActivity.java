package com.cs2340.goodwillhunting.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Location;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PopActivity extends Activity {
    Button close;
    private static final String TAG = "PopActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
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

        getWindow().setLayout((int) (width*.6), (int) (height*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

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
        //TextView name = findViewById(R.id.store_Name);
        TextView add = findViewById(R.id.store_address);
        TextView c = findViewById(R.id.store_city);
        TextView k = findViewById(R.id.store_key);
        TextView l = findViewById(R.id.store_latitude);
        //TextView lon = findViewById(R.id.store_longitude);
        TextView p = findViewById(R.id.store_phone);
        TextView st = findViewById(R.id.store_state);
        TextView t = findViewById(R.id.store_type);
        TextView w = findViewById(R.id.store_website);
        TextView z = findViewById(R.id.store_zip);

        //name.setText(storeName);
        add.setText(address);
        c.setText(city + ", "+ state + " " + zip);
        k.setText(key);
        l.setText(latitude + ", " + longitude);
        //lon.setText(longitude);
        p.setText(phone);
        st.setText(state);
        t.setText(type);
        w.setText(website);
        z.setText(zip);


    }
}
