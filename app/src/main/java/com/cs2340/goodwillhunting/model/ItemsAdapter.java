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

/**
 * Class to adapt items
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private static final String TAG = "ItemsAdapter";
    Context context;
    ArrayList<Item> items;

    /**
     * Constructor to invoke an item adapter
     * @param c
     * @param it
     */
    public ItemsAdapter(Context c, ArrayList<Item> it) {
        context = c;
        items = it;
    }

    /**
     * method to create a view holder
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_layout, viewGroup, false));

    }

    /**
     * method to bind item information to a view holder
     * @param myViewHolder myViewHolder to describe an item view
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.itemName.setText(items.get(i).getName());
        myViewHolder.itemDescription.setText(items.get(i).getDescription());
        myViewHolder.itemQty.setText(""+items.get(i).getQuantity());
       // myViewHolder.itemQty.setText(items.get(i).getQuantity());
        myViewHolder.item_parent_layout.setOnClickListener(new View.OnClickListener() {

            /**
             * onclick method to set item data on the click
             * @param v current view
             */
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

    /**
     * method to return number of items
     * @return number of items
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Class to create a myViewHolder to describe an item view
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item_parent_layout;
        TextView itemName;
        TextView itemDescription;
        TextView itemQty;

        /**
         * Constructor to create a myViewHolder
         * @param itemView the itemview
         */
        public MyViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemDescription = (TextView) itemView.findViewById(R.id.itemDescription);
            itemQty = (TextView) itemView.findViewById(R.id.itemQty);
            item_parent_layout = itemView.findViewById(R.id.item_parent_layout);

        }

    }
}
