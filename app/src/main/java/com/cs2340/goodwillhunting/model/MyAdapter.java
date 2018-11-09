package com.cs2340.goodwillhunting.model;

import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.controllers.LocationDetailActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Class for interface adapter
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final String TAG = "MyAdapter";
    Context context;
    ArrayList<Location> locations;
    String itemName;
    String category;

    /**
     * Constructor to create MyAdapter
     * @param c
     * @param l
     * @param i
     * @param cat
     */
    public MyAdapter(Context c, ArrayList<Location> l, String i, String cat) {
        context = c;
        locations = l;
        itemName = i;
        category = cat;
    }

    /**
     * methos to create a myViewHolder to describe an iten view
     * @param viewGroup
     * @param i
     * @return the item view
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.location_list_content, viewGroup, false));

    }

    /**
     * method to bind item information to a view holder
     * @param myViewHolder myViewHolder to describe an item view
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.name.setText(locations.get(i).getName());
        myViewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {

            /**
             * onclick method to set item data on the click
             * @param v current view
             */
            @Override
            public void onClick(View v) {
                Toast.makeText(context, locations.get(i).getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, LocationDetailActivity.class);
                intent.putExtra("location_name", locations.get(i).getName());
                if (itemName != null) {
                    intent.putExtra("item_name", itemName);
                }

                if (category != null) {
                    intent.putExtra("category", category);
                }
                context.startActivity(intent);
            }
        });

    }

    /**
     * method to return number of items
     * @return number of items
     */
    @Override
    public int getItemCount() {
        return locations.size();
    }

    /**
     * Class to create a myViewHolder to describe an item view
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parent_layout;
        TextView name;

        /**
         * Constructor to create a myViewHolder
         * @param itemView the itemview
         */
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }

    }
}
