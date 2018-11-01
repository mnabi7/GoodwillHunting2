package com.cs2340.goodwillhunting.controllers;
import com.cs2340.goodwillhunting.model.Location;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.cs2340.goodwillhunting.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdate;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        reference = FirebaseDatabase.getInstance().getReference().child("locations");
        displayLocations();
    }

    private void displayLocations() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double avgLat = 0, avgLon = 0;
                double count = 0;
                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    Location loc = snapShot.getValue(Location.class);
                    //Log.d("MapActivity", "Name: " + loc.getName() + "\tLatitude: " + loc.getLatitude() + "\tLongititude: " + loc.getLongitude() + "\n");
                    double lat = Double.parseDouble(loc.getLatitude());
                    double lon = Double.parseDouble(loc.getLongitude());
                    avgLat += lat;
                    avgLon += lon;
                    count++;
                    LatLng locCoord = new LatLng(lat, lon);
                    String title = loc.getName();
                    String snippet = loc.getPhone();
                    mMap.addMarker(new MarkerOptions().position(locCoord).title(title).snippet(snippet));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(locCoord));
                }
                CameraUpdate center=
                        CameraUpdateFactory.newLatLng(new LatLng(avgLat/count, avgLon/count));
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);
                mMap.moveCamera(center);
                mMap.animateCamera(zoom);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
