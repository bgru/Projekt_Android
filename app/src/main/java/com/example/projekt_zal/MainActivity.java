package com.example.projekt_zal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    protected List<NameValueItem> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreteNotifChannel();


        nameList = loadList();
        if (nameList == null) {
            nameList = new ArrayList<>();
        }

        // Sample data
//        nameList = new ArrayList<>();
//        nameList.add(new NameValueItem("Activity 1", 1));
//        nameList.add(new NameValueItem("Activity 2", 2));
    }

    public void addButton(View view) {
        EditText nameEditText = findViewById(R.id.nameTextBox);
        EditText arg1EditText = findViewById(R.id.arg1TextBox);
        EditText arg2EditText = findViewById(R.id.arg2TextBox);

        String name = nameEditText.getText().toString();
        String arg1 = arg1EditText.getText().toString();
        String arg2 = arg2EditText.getText().toString();

        TextView nameLabel = findViewById(R.id.nameLabel);
        TextView sumLabel = findViewById(R.id.sumLabel);

        if (!name.isEmpty() && !arg1.isEmpty() && !arg2.isEmpty()) {
            try {
                int tempSum = Integer.parseInt(arg1) + Integer.parseInt(arg2);
                nameLabel.setText(name);
                sumLabel.setText(String.valueOf(tempSum));

                nameList.add(new NameValueItem(name, tempSum));
                saveList(nameList);
            } catch (NumberFormatException e) {
                showToast("Please enter valid numbers.");
            }
        } else {
            showToast("Fields cannot be empty!");
        }
    }


    public void displayButton(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("nameList", (Serializable) nameList);
        startActivity(intent);
    }

    public void clearButton(View view) {
        nameList.clear();

        TextView nameLabel = findViewById(R.id.nameLabel);
        TextView sumLabel = findViewById(R.id.sumLabel);

        nameLabel.setText("");
        sumLabel.setText("");

        showToast("List and fields cleared!");
    }

    // Method to save the list
    private void saveList(List<NameValueItem> list) {
        SharedPreferences prefs = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("NameValueList", json);
        editor.apply();
    }

    // Method to load the list
    private List<NameValueItem> loadList() {
        SharedPreferences prefs = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("NameValueList", null);
        Type type = new TypeToken<ArrayList<NameValueItem>>() {}.getType();
        return gson.fromJson(json, type);
    }

    protected void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected int stopCount = 0;

    protected void CreteNotifChannel(){
        NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        if (manager != null) {
            manager.createNotificationChannel(channel);
        }
    }

    protected void makeNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Zatrzymanie aktywności")
                .setContentText("Liczba zatrzymań: " + stopCount)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopCount++;
        makeNotification();
    }


}