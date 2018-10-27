package com.cs2340.goodwillhunting.controllers;
import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Location;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.cs2340.goodwillhunting.model.MyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


// Shows all the locations
public class LocationListActivity extends Activity {

    private static final String TAG = "LocationList";
    private Button logOut;
    private Button search;
    private DatabaseReference reference;
    private DatabaseReference reference2;
    RecyclerView recyclerView;
    ArrayList<Location> list;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        reference2 = FirebaseDatabase.getInstance().getReference().child("locations");
        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        //assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<Location>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Location l = dataSnapshot1.getValue(Location.class);
                    list.add(l);
                }
                adapter = new MyAdapter(LocationListActivity.this, list, null, null);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(LocationListActivity.this, "Idk wtf i am doing, i just hope this works", Toast.LENGTH_SHORT).show();

            }
        });
        logOut = findViewById(R.id.button_logout);
        search = findViewById(R.id.button_search);

        reference = FirebaseDatabase.getInstance().getReference();

        //InputStream inputStream = getResources().openRawResource(R.raw.locationdata);
        //CSVFile csvFile = new CSVFile(inputStream);
        //List locs = csvFile.read();

        //DatabaseReference itemRef = FirebaseDatabase.getInstance().getReference().child("items");

        /*for (int i = 1; i < locs.size(); i++) {
            String[] row = (String[]) locs.get(i);
            Location loc = new Location(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4], row[5], row[6],
                    row[7], row[8], row[9], row[10]);
            reference.child("locations").child(Integer.toString(loc.getKey())).setValue(loc);
            itemRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }*/


    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference userRef = reference.child("user");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userType = dataSnapshot.getValue(String.class);
                Toast.makeText(LocationListActivity.this, userType, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        userRef.addListenerForSingleValueEvent(valueEventListener);


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationListActivity.this, WelcomeScreenActivity.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationListActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


    }
}
