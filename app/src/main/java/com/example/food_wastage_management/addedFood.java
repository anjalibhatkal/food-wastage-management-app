package com.example.food_wastage_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addedFood extends AppCompatActivity {

    private EditText custName;
    private EditText custPhone;
    private EditText custAddress;
    private EditText custZip;
    private EditText foodType;
    private EditText foodQuantity;
    private EditText custEmail;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added);

        custName = findViewById(R.id.customer_name);
        custPhone = findViewById(R.id.customer_phone);
        custAddress = findViewById(R.id.customer_address);
        custZip = findViewById(R.id.customer_zipcode);
        foodType = findViewById(R.id.food_type);
        foodQuantity = findViewById(R.id.food_quantity);
        custEmail = findViewById(R.id.customer_email);
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerName = custName.getText().toString().trim();
                String customerEmailValue = custEmail.getText().toString().trim();
                String customerPhoneText = custPhone.getText().toString().trim();
                String customerAddress = custAddress.getText().toString().trim();
                String customerZipCodeText = custZip.getText().toString().trim();
                String foodTypeValue = foodType.getText().toString().trim();
                String foodQuantityText = foodQuantity.getText().toString().trim();

                if (customerName.isEmpty() || customerPhoneText.isEmpty() || customerAddress.isEmpty() ||
                        customerZipCodeText.isEmpty() || foodTypeValue.isEmpty() || foodQuantityText.isEmpty() ||
                        customerEmailValue.isEmpty()) {
                    // Display a Toast message
                    Toast.makeText(addedFood.this, "Please enter all the details", Toast.LENGTH_LONG).show();
                    return; // Exit the function early
                } else {
                    // Convert the phone number and zip code to integers
                    int customerPhone = Integer.parseInt(customerPhoneText);
                    int customerZipCode = Integer.parseInt(customerZipCodeText);
                    int foodQuantityValue = Integer.parseInt(foodQuantityText);

                    // Create an instance of your database helper
                    MyDatabaseHelper myDB = new MyDatabaseHelper(addedFood.this);
                    myDB.addFood(customerName, customerEmailValue, customerPhone, customerAddress, customerZipCode, foodTypeValue, foodQuantityValue);

                    // Optionally, you can clear the EditText fields after adding the data
                    custName.setText("");
                    custPhone.setText("");
                    custAddress.setText("");
                    custZip.setText("");
                    foodType.setText("");
                    foodQuantity.setText("");
                    custEmail.setText("");
                }
            }
        });
    }
}