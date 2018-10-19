package com.cs2340.goodwillhunting.controllers;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Location;
import com.cs2340.goodwillhunting.model.Model;
//import com.cs2340.goodwillhunting.controllers.LocationListActivity.SimpleLocationRecyclerViewAdapter;

public class LocationDetailFragment extends Fragment {

    public static final String ARG_LOCATION_KEY = "location_key";

    private Location mLocation;
    private TextView textView;

    public LocationDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if we got a valid key passed to us
        if (getArguments().containsKey(ARG_LOCATION_KEY)) {
            // Get the id from the intent arguments (bundle) and
            //ask the model to give us the course object
            Model model = Model.getInstance();
            // mCourse = model.getCourseById(getArguments().getInt(ARG_COURSE_ID));
            mLocation = model.getCurrLocation();
            Log.d("CourseDetailFragment", "Passing over location: " + mLocation.getName());

            Activity activity = this.getActivity();

            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mLocation.getName());
            }
            Log.d("CourseDetailFragment", mLocation.toString());

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_detail, container, false);

        //Step 1.  Setup the text view by getting it from our layout in the main window
        textView = rootView.findViewById(R.id.details);
        showText(mLocation.toString());

        return rootView;
    }

    public void showText(String text) {
        Log.d("Show Text", text);
        textView.setText(text);
    }


}