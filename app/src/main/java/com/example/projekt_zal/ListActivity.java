package com.example.projekt_zal;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListActivity extends MainActivity {

    private List<NameValueItem> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        nameList = (List<NameValueItem>) getIntent().getSerializableExtra("nameList");
        nameList = setListIdValues(nameList);

        ListView listView = findViewById(R.id.listView);
        ItemListAdapter adapter = new ItemListAdapter(this, nameList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            NameValueItem item = adapter.getItem(position);
            if (item != null) {
                String message = "Name: " + item.getName() + ", Value: " + item.getValue();
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void deleteButton(View view) {
        // GET ID OF CUSTOM LIST ITEM HERE
        int id = 0;
        for (NameValueItem a: nameList
             ) {
            if (a.getId() == id){
                removeItemFromPreferences(a);
                break;
            }
        }
    }




    public void removeItemFromPreferences(NameValueItem itemToRemove) {

        if (nameList.contains(itemToRemove)) {
            showToast("Item found");
        }
        saveList(nameList);
    }


}
