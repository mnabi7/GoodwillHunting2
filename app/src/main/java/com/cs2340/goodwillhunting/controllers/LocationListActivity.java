package com.cs2340.goodwillhunting.controllers;
import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Location;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.util.List;

import com.cs2340.goodwillhunting.model.Model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


// Shows all the locations
public class LocationListActivity extends Activity {

    private static final String TAG = "MainMenu";
    private Button logOut;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        logOut = findViewById(R.id.button_logout);

        reference = FirebaseDatabase.getInstance().getReference();

        InputStream inputStream = getResources().openRawResource(R.raw.locationdata);
        CSVFile csvFile = new CSVFile(inputStream);
        List locs = csvFile.read();

        for (int i = 1; i < locs.size(); i++) {
            String[] row = (String[]) locs.get(i);
            Location loc = new Location(row[0], row[1], row[2], row[3], row[4], row[5], row[6],
                    row[7], row[8], row[9], row[10]);
            reference.child("locations").child(loc.getKey()).setValue(loc);
            Model.getInstance().addLocation(loc);
        }

        View recyclerView = findViewById(R.id.location_list);
        assert recyclerView != null;

        setupRecyclerView((RecyclerView) recyclerView);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //Log.d(TAG, LoggedInUser.getInstance().getUserType().toString());
        //Toast.makeText(LocationListActivity.this, "This user is a "
        //        + LoggedInUser.getInstance().getUserType().toString(), Toast.LENGTH_LONG).show();


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationListActivity.this, WelcomeScreenActivity.class);
                startActivity(intent);
            }
        });


    }

    /**
     * Set up an adapter and hook it to the provided view
     * @param recyclerView  the view that needs this adapter
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Model model = Model.getInstance();
        recyclerView.setAdapter(new SimpleLocationRecyclerViewAdapter(model.getLocations()));
    }

    /**
     * This inner class is our custom adapter.  It takes our basic model information and
     * converts it to the correct layout for this view.
     *
     * In this case, we are just mapping the toString of the Course object to a text field.
     */
    public class SimpleLocationRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleLocationRecyclerViewAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<Location> mLocations;

        /**
         * set the items to be used by the adapter
         *
         * @param items the list of items to be displayed in the recycler view
         */
        public SimpleLocationRecyclerViewAdapter(List<Location> items) {
            mLocations = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /*

              This sets up the view for each individual item in the recycler display
              To edit the actual layout, we would look at: res/layout/course_list_content.xml
              If you look at the example file, you will see it currently just 2 TextView elements
             */
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.location_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            final Model model = Model.getInstance();
            /*
            This is where we have to bind each data element in the list (given by position parameter)
            to an element in the view (which is one of our two TextView widgets
             */
            //start by getting the element at the correct position
            holder.mLocation = mLocations.get(position);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              textview and the string rep of a course in the other.
             */
            holder.mKeyView.setText("" + mLocations.get(position).getKey());
            holder.mContentView.setText(mLocations.get(position).getName());

            Log.d(TAG, "HAVE NOT CLICKED YET");

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "CLICKED");
                    //on a phone, we need to change windows to the detail view
                    Context context = v.getContext();
                    //create our new intent with the new screen (activity)
                    Intent intent = new Intent(context, LocationDetailActivity.class);

                       // pass along the id of the course so we can retrieve the correct data in
                       // the next window

                    intent.putExtra(LocationDetailFragment.ARG_LOCATION_KEY, holder.mLocation.getKey());

                    model.setCurrLocation(holder.mLocation);

                    //now just display the new window
                    context.startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {

            return mLocations.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Course) and the widgets in
         * the list view (in this case the two TextView)
         */

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mKeyView;
            public final TextView mContentView;
            public Location mLocation;

            public ViewHolder(View view) {
                super(view);

                mView = view;
                mKeyView = (TextView) view.findViewById(R.id.key);
                mContentView = (TextView) view.findViewById(R.id.content);


            }



            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }


}
