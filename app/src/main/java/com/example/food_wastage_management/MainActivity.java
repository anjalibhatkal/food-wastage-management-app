package com.example.food_wastage_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button shareButton = findViewById(R.id.shareButton);
        Button searchButton = findViewById(R.id.searchButton);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open AddedFoodActivity when "Share My Food" button is clicked
                Intent intent = new Intent(MainActivity.this, addedFood.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open ReceivedFoodActivity when "Search for Food" button is clicked
                Intent intent = new Intent(MainActivity.this, receivedFood.class);
                startActivity(intent);
            }
        });
    }
}