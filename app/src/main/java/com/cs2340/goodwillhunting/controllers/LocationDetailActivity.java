package com.cs2340.goodwillhunting.controllers;

import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Location;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;


/**
 * An activity representing a single Location detail screen. This
 * activity is only used narrow width devices.
 * in a {@link LocationListActivity}.
 *
 * Here we need to display a list of location info.  Our view will be pretty similar
 * since we are displaying a list of location info  We are going to use a
 * recycler view again.
 */
public class LocationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        //setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putInt(LocationDetailFragment.ARG_LOCATION_KEY,
                    getIntent().getIntExtra(LocationDetailFragment.ARG_LOCATION_KEY, 0));

            LocationDetailFragment fragment = new LocationDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.location_detail_container, fragment).commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown.
            navigateUpTo(new Intent(this, LocationListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
