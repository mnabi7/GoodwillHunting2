package com.cs2340.goodwillhunting.controllers;

import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.model.Location;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final String TAG = "MyAdapter";
    Context context;
    ArrayList<Location> locations;

    public MyAdapter(Context c, ArrayList<Location> l) {
        context = c;
        locations = l;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.location_list_content, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.name.setText(locations.get(i).getName());
        myViewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, locations.get(i).getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, LocationDetailActivity.class);
                intent.putExtra("location_name", locations.get(i).getName());
                context.startActivity(intent);
            }
        });
//        myViewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: Clicked on " + stores.get(i).getName());
//
//                //Intent intent = new Intent(context, StoreDetailsActivity.class);
//               // intent.putExtra("store_name", stores.get(i).getName());
//               // context.startActivity(intent);
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parent_layout;
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }

    }
}
