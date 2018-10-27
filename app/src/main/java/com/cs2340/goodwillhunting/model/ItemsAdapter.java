package com.cs2340.goodwillhunting.model;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.controllers.ItemDetailActivity;
import com.cs2340.goodwillhunting.controllers.LocationDetailActivity;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private static final String TAG = "ItemsAdapter";
    Context context;
    ArrayList<Item> items;

    public ItemsAdapter(Context c, ArrayList<Item> it) {
        context = c;
        items = it;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_layout, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.itemName.setText(items.get(i).getName());
        myViewHolder.itemDescription.setText(items.get(i).getDescription());
        myViewHolder.itemQty.setText(""+items.get(i).getQuantity());
       // myViewHolder.itemQty.setText(items.get(i).getQuantity());
        myViewHolder.item_parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, items.get(i).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra("item_name", items.get(i).getName());
                intent.putExtra("item_loc", Integer.toString(items.get(i).getLocation()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item_parent_layout;
        TextView itemName;
        TextView itemDescription;
        TextView itemQty;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemDescription = (TextView) itemView.findViewById(R.id.itemDescription);
            itemQty = (TextView) itemView.findViewById(R.id.itemQty);
            item_parent_layout = itemView.findViewById(R.id.item_parent_layout);

        }

    }
}
