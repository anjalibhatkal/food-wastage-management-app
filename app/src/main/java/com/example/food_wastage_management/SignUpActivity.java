package com.example.food_wastage_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        signUpButton = findViewById(R.id.signup);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input for username and password
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.matches("")) {
                    Toast.makeText(SignUpActivity.this, "Username cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!isvalidPassword(password)) {
                    Toast.makeText(SignUpActivity.this, "Invalid Password", Toast.LENGTH_LONG).show();
                    return;
                }

                // Add the user to your database (you can use your MyDatabaseHelper class)
                boolean userAdded = addNewUser(username, password);

                if (userAdded) {
                    // Successful signup, navigate to the login activity
                    Intent intent = new Intent(SignUpActivity.this, signIn.class);
                    Toast.makeText(SignUpActivity.this, "Registration successful", Toast.LENGTH_LONG).show();

                    startActivity(intent);
                    finish(); // Finish the signup activity
                } else {
                    Toast.makeText(SignUpActivity.this, "Registration failed. Please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean addNewUser(String username, String password) {
        // Implement your logic to add a new user to the database
        // Return true if the user is added successfully, false otherwise
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        return dbHelper.addUser(username, password);
    }

    Pattern lowercase = Pattern.compile("^.[a-z].$");
    Pattern uppercase = Pattern.compile("^.[A-Z].$");
    Pattern number = Pattern.compile("^.[0-9].$");
    Pattern special = Pattern.compile("^.[@#$%^&(){},.;/].*$");
    private boolean isvalidPassword(String userpwd) {
        // Check if the password has at least 8 characters
        if (userpwd.length() < 8) {
            return false;
        }

        // Check for at least one lowercase letter
        if (!userpwd.matches(".*[a-z].*")) {
            return false;
        }

        // Check for at least one uppercase letter
        if (!userpwd.matches(".*[A-Z].*")) {
            return false;
        }

        // Check for at least one digit
        if (!userpwd.matches(".*\\d.*")) {
            return false;
        }

        // Check for at least one special character from your specified set
        if (!userpwd.matches(".*[@#$%^&(){},.;/].*")) {
            return false;
        }

        return true;
    }

}
