package com.example.food_wastage_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class receivedFood extends AppCompatActivity implements CustomAdapter.OnRecycleClickListener {
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<Integer> cust_ID;
    ArrayList<String> cust_name, cust_email, cust_phone, cust_address, cust_zip, food_typeVal, food_quantityVal;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received);

        myDB = new MyDatabaseHelper(receivedFood.this);

        cust_ID = new ArrayList<>();
        cust_name = new ArrayList<>();
        cust_email = new ArrayList<>();
        cust_phone = new ArrayList<>();
        cust_address = new ArrayList<>();
        cust_zip = new ArrayList<>();
        food_typeVal = new ArrayList<>();
        food_quantityVal = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        customAdapter = new CustomAdapter(this, cust_ID, cust_name, cust_email, cust_phone, cust_address, cust_zip, food_typeVal, food_quantityVal);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter.setOnRecycleClickListener(this);

        TextView headerTitle = findViewById(R.id.headerTitle);
        headerTitle.setText("Available Food");

        displayFood();
    }

    void displayFood() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(receivedFood.this, "No food donated yet!", Toast.LENGTH_LONG).show();
            return;
        } else {
            int columnIndexID = cursor.getColumnIndex("customer_id");
            int columnIndexName = cursor.getColumnIndex("customer_name");
            int columnIndexEmail = cursor.getColumnIndex("customer_email");
            int columnIndexPhone = cursor.getColumnIndex("customer_phno");
            int columnIndexAddress = cursor.getColumnIndex("customer_address");
            int columnIndexZip = cursor.getColumnIndex("customer_zip");
            int columnIndexFoodType = cursor.getColumnIndex("food_type");
            int columnIndexFoodQuantity = cursor.getColumnIndex("food_quantity");

            while (cursor.moveToNext()) {
                // Add retrieved data to the respective ArrayLists
                cust_ID.add(cursor.getInt(columnIndexID));
                cust_name.add(cursor.getString(columnIndexName));
                cust_email.add(cursor.getString(columnIndexEmail));
                cust_phone.add(cursor.getString(columnIndexPhone));
                cust_address.add(cursor.getString(columnIndexAddress));
                cust_zip.add(cursor.getString(columnIndexZip));
                food_typeVal.add(cursor.getString(columnIndexFoodType));
                food_quantityVal.add(cursor.getString(columnIndexFoodQuantity));
            }
        }
        cursor.close(); // Close the cursor after using it
    }

    @Override
    public void onRecycleClick(int position) {
        int idToDelete = cust_ID.get(position);
        boolean isDeleted = myDB.deleteFood(idToDelete);

        if (isDeleted) {
            cust_ID.remove(position);
            cust_name.remove(position);
            cust_email.remove(position);
            cust_phone.remove(position);
            cust_address.remove(position);
            cust_zip.remove(position);
            food_typeVal.remove(position);
            food_quantityVal.remove(position);

            customAdapter.notifyItemRemoved(position);
            Toast.makeText(this, "Request Received. Food item will be delivered soon.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to process your request.", Toast.LENGTH_SHORT).show();
        }
    }
}
