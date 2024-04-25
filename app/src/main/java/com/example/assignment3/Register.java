package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class Register extends AppCompatActivity {

    EditText email;
    EditText pass;
    TextView sign;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        sign=findViewById(R.id.sign);
        btn=findViewById(R.id.btn);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Register.this, Login.class);
               startActivity(intent);


            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerModel customerModel;

                try {
                    customerModel=new CustomerModel(-1,email.getText().toString(),pass.getText().toString());

                    Toast.makeText(Register.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    customerModel=new CustomerModel(-1,"Error","Error");

                    Toast.makeText(Register.this, "Error Creating Customer", Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper databaseHelper= new DatabaseHelper(Register.this);

                boolean success = databaseHelper.addOne(customerModel);

                Toast.makeText(Register.this, "Success: "+ success, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(Register.this, MainActivity.class);
                //intent.putExtra("newCustomer", customerModel);
                startActivity(intent);
            }
        });


    }
}