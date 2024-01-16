package com.example.projekt_zal;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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


}
