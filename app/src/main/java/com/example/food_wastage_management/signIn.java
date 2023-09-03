package com.example.food_wastage_management;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signIn extends AppCompatActivity {
    EditText eusername, epwd;
    Button login;
    MyDatabaseHelper myDB = new MyDatabaseHelper(signIn.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        eusername = (EditText) findViewById(R.id.username2);
        epwd = (EditText) findViewById(R.id.password2);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newemail = eusername.getText().toString();
                String newpwd = epwd.getText().toString();

                // Check user's credentials against the "users" table and capture the result
                boolean loginSuccess = myDB.checkUserCredentials(newemail, newpwd);

                if (loginSuccess) {
                    // Successful login
                    Toast.makeText(signIn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(signIn.this, MainActivity.class);
                    startActivity(intent);
                    eusername.setText("");
                    epwd.setText("");
                } else {
                    Toast.makeText(signIn.this, "Login Failed. Try Again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
