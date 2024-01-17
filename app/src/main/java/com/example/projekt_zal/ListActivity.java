package com.example.projekt_zal;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        List<NameValueItem> nameList = (List<NameValueItem>) getIntent().getSerializableExtra("nameList");

        ListView listView = findViewById(R.id.listView);
        CustomListAdapter adapter = new CustomListAdapter(this, nameList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            NameValueItem item = adapter.getItem(position);
            if (item != null) {
                String message = "Name: " + item.getName() + ", Value: " + item.getValue();
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteButton(View view, int position) {
        ListView listView = findViewById(R.id.listView);

        // Get the item to remove
        NameValueItem itemToRemove = ((CustomListAdapter) listView.getAdapter()).getItem(position);
        assert itemToRemove != null;
        showToast("" + itemToRemove.getName());
        showToast("" + nameList.indexOf(itemToRemove));

        // Remove the item from the adapter and update the list
        ((CustomListAdapter) listView.getAdapter()).remove(itemToRemove);
        ((CustomListAdapter) listView.getAdapter()).notifyDataSetChanged();

        // Remove the item from SharedPreferences
        removeItemFromPreferences(itemToRemove);


//        // Find the position of the item in the ListView
//        View parentRow = (View) view.getParent();
//
//        ListView listView = (ListView) parentRow.getParent();
//        final int position = listView.getPositionForView(parentRow);
//
//        // Get the item to remove
//        NameValueItem itemToRemove = ((CustomListAdapter) listView.getAdapter()).getItem(position);
//
//
//        // Remove the item from the adapter and update the list
//        ((CustomListAdapter) listView.getAdapter()).remove(itemToRemove);
//        ((CustomListAdapter) listView.getAdapter()).notifyDataSetChanged();
//
//        // Remove the item from SharedPreferences
//        removeItemFromPreferences(itemToRemove);
    }


    public void removeItemFromPreferences(NameValueItem itemToRemove) {

        if (nameList.contains(itemToRemove)) {
            showToast("Item found");
        }
        saveList(nameList);
    }


}
