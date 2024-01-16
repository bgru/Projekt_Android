package com.example.projekt_zal;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    protected static int stopCount;
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

    @SuppressLint("SetTextI18n")
    public void clearButton(View view) {
        nameList.clear();

        TextView nameLabel = findViewById(R.id.nameLabel);
        TextView sumLabel = findViewById(R.id.sumLabel);

        nameLabel.setText("No_Data");
        sumLabel.setText("No_Data");

        showToast("List and fields cleared!");
    }

    public void clearTexFields(View view){
        EditText nameEditText = findViewById(R.id.nameTextBox);
        EditText arg1EditText = findViewById(R.id.arg1TextBox);
        EditText arg2EditText = findViewById(R.id.arg2TextBox);
        nameEditText.setText("");
        arg1EditText.setText("");
        arg2EditText.setText("");
    }
    protected void saveList(List<NameValueItem> list) {
        SharedPreferences prefs = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("NameValueList", json);
        editor.apply();
    }

    protected List<NameValueItem> loadList() {
        SharedPreferences prefs = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("NameValueList", null);
        Type type = new TypeToken<ArrayList<NameValueItem>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    protected void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    protected void CreteNotifChannel() {
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
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            showToast("The app does not have permissions to show Notifications");
            return;
        }
        notificationManager.notify(1, builder.build());
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopCount++;
        makeNotification();
    }

    public void beautify(View view) {

    }


}