package com.example.projekt_zal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.projekt_zal.NameValueItem;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<NameValueItem> {

    public CustomListAdapter(Context context, List<NameValueItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_item, parent, false);
        }

        TextView itemNameTextView = convertView.findViewById(R.id.itemNameTextView);
        TextView itemValueTextView = convertView.findViewById(R.id.itemValueTextView);

        NameValueItem currentItem = getItem(position);
        if (currentItem != null) {
            itemNameTextView.setText(currentItem.getName());
            itemValueTextView.setText(String.valueOf(currentItem.getValue()));
        }

        Button deleteButton = convertView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(v -> {
            remove(getItem(position));
            notifyDataSetChanged();
        });

        return convertView;
    }
}
