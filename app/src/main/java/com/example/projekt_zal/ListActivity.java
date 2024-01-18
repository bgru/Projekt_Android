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

    //TODO: Deleting a row also deletes it from Shared Preferences
//    public void deleteButton(View view) {
//        Integer Id = ItemListAdapter.ID;
//        showToast(""+Id);
//        for (NameValueItem a: nameList) {
//            if(a.getId() == Id){
//                removeItemFromPreferences(a);
//                return;
//            }
//        }
//        showToast("Item Not Found!");
//    }
//
//
//    public void removeItemFromPreferences(NameValueItem itemToRemove) {
//
//        if (nameList.contains(itemToRemove)) {
//            showToast("Item removed");
//        }
//        nameList.remove(itemToRemove);
//        saveList(nameList);
//    }


}
