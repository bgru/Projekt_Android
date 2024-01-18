package com.example.projekt_zal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ItemListAdapter extends ArrayAdapter<NameValueItem> {

    public ItemListAdapter(Context context, List<NameValueItem> items) {
        super(context, 0, items);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_item, parent, false);
        }

        TextView itemNameTextView = convertView.findViewById(R.id.itemNameTextView);
        TextView itemValueTextView = convertView.findViewById(R.id.itemValueTextView);
        TextView itemIdTextView = convertView.findViewById(R.id.itemId);

        NameValueItem currentItem = getItem(position);
        if (currentItem != null) {
            itemNameTextView.setText(currentItem.getName());
            itemValueTextView.setText(String.valueOf(currentItem.getValue()));
            itemIdTextView.setText(String.valueOf(currentItem.getId()));

        }

        Button deleteButton = convertView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(v -> {
            remove(getItem(position));
            notifyDataSetChanged();

        });
        deleteButton.setFocusable(false);
        deleteButton.setFocusableInTouchMode(false);

        return convertView;
    }
}
