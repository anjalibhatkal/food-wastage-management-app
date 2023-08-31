package com.example.food_wastage_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class receivedFood extends AppCompatActivity {
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<String> cust_ID, cust_name, cust_email, cust_phone, cust_address, cust_zip,  food_typeVal,  food_quantityVal;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received);

        recyclerView = findViewById(R.id.recyclerView);
        myDB = new MyDatabaseHelper(receivedFood.this);

        cust_ID = new ArrayList<>();
        cust_name = new ArrayList<>();
        cust_email = new ArrayList<>();
        cust_phone = new ArrayList<>();
        cust_address = new ArrayList<>();
        cust_zip = new ArrayList<>();
        food_typeVal = new ArrayList<>();
        food_quantityVal = new ArrayList<>();

        // First, populate the ArrayLists by calling the displayFood() method
        displayFood();

        // Now that the ArrayLists are populated, set up the RecyclerView adapter
        customAdapter = new CustomAdapter(receivedFood.this, cust_ID, cust_name, cust_email, cust_phone, cust_address, cust_zip, food_typeVal, food_quantityVal);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(receivedFood.this));
    }


    void displayFood() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No food donated yet!", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                // Add retrieved data to the respective ArrayLists
                cust_ID.add(cursor.getString(0));
                cust_name.add(cursor.getString(1));
                cust_email.add(cursor.getString(2));
                cust_phone.add(cursor.getString(3));
                cust_address.add(cursor.getString(4));
                cust_zip.add(cursor.getString(5));
                food_typeVal.add(cursor.getString(6));
                food_quantityVal.add(cursor.getString(7));

            }
            // Now you have the data populated in the ArrayLists
        }
        cursor.close(); // Close the cursor after using it
    }
}