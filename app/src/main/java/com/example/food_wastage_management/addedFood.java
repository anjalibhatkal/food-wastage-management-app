package com.example.food_wastage_management;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added);

        custName = findViewById(R.id.customer_name);
        custEmail = findViewById(R.id.customer_email);
        custPhone = findViewById(R.id.customer_phone);
        custAddress = findViewById(R.id.customer_address);
        custZip = findViewById(R.id.customer_zipcode);
        foodType = findViewById(R.id.food_type);
        foodQuantity = findViewById(R.id.food_quantity);

        addButton = findViewById(R.id.add_button);
        myDB = new MyDatabaseHelper(addedFood.this);

        TextView headerTitle = findViewById(R.id.headerTitle);
        headerTitle.setText("Share Food");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerName = custName.getText().toString().trim();
                String customerEmailValue = custEmail.getText().toString().trim();
                String customerPhone = custPhone.getText().toString().trim();
                String customerAddress = custAddress.getText().toString().trim();
                String customerZipCode = custZip.getText().toString().trim();
                String foodTypeValue = foodType.getText().toString().trim();
                String foodQuantityValue = foodQuantity.getText().toString().trim();

                if (customerName.isEmpty() || customerAddress.isEmpty() ||
                        foodTypeValue.isEmpty() || customerEmailValue.isEmpty()) {
                    // Display a Toast message
                    Toast.makeText(addedFood.this, "Please enter all the details", Toast.LENGTH_LONG).show();
                    return; // Exit the function early
                }

                // Validate phone number
                if (customerPhone.length() != 10) {
                    Toast.makeText(addedFood.this, "Please enter a valid 10-digit phone number", Toast.LENGTH_LONG).show();
                    return;
                }

                // Validate ZIP code
                if (customerZipCode.length() != 6) {
                    Toast.makeText(addedFood.this, "Please enter a valid 5-digit ZIP code", Toast.LENGTH_LONG).show();
                    return;
                }

                // Validate food quantity
                try {
                    int parsedQuantity = Integer.parseInt(foodQuantityValue);
                    if (parsedQuantity <= 0) {
                        Toast.makeText(addedFood.this, "Please enter a valid positive food quantity", Toast.LENGTH_LONG).show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(addedFood.this, "Invalid food quantity format", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkinsertdata = myDB.addFood(customerName, customerEmailValue, customerPhone, customerAddress, customerZipCode, foodTypeValue, foodQuantityValue);
                if (checkinsertdata == true)
                    Toast.makeText(addedFood.this, "Food Details Submitted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(addedFood.this, "Failed to submit.", Toast.LENGTH_LONG).show();
            }
        });

    }
}
