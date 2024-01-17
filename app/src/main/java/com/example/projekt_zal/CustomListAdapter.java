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

public class CustomListAdapter extends ArrayAdapter<NameValueItem> {

    public CustomListAdapter(Context context, List<NameValueItem> items) {
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

        NameValueItem currentItem = getItem(position);
        if (currentItem != null) {
            itemNameTextView.setText(currentItem.getName());
            itemValueTextView.setText(String.valueOf(currentItem.getValue()));
        }

        Button deleteButton = convertView.findViewById(R.id.deleteButton);
        deleteButton.setFocusable(false);
        deleteButton.setFocusableInTouchMode(false);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListActivity) getContext()).deleteButton(v, position);
            }
        });


//        deleteButton.setOnClickListener(v -> {
//            NameValueItem itemToRemove = getItem(position);
//            remove(itemToRemove);
//            notifyDataSetChanged();
//            assert itemToRemove != null;
//            ((ListActivity) getContext()).removeItemFromPreferences(itemToRemove);
//        });

        return convertView;
    }
}
