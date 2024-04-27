package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.LocusId;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    ImageView backbtn;
    EditText email;
    EditText pass;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backbtn=findViewById(R.id.backbtn);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        btn2=findViewById(R.id.btn2);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString().trim();
                String userPass = pass.getText().toString().trim();

                // Check if the email and password are not empty
                if (!userEmail.isEmpty() && !userPass.isEmpty()) {
                    // Query the database to check if the email and password match
                    DatabaseHelper databaseHelper = new DatabaseHelper(Login.this);
                    databaseHelper.open();
                    boolean isValid = databaseHelper.checkUser(userEmail, userPass);
                    databaseHelper.close();

                    if (isValid) {
                        // Navigate to MainActivity
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        // Display error message
                        Toast.makeText(Login.this, "Account not found or password incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Display error message if email or password is empty
                    Toast.makeText(Login.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}