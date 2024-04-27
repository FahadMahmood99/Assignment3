package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<CustomerModel> customerModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        DatabaseHelper databaseHelper1=new DatabaseHelper(MainActivity.this);
        List<CustomerModel> everyone=databaseHelper1.getEveryone();

        customerModel = new ArrayList<>(everyone);

        //Setting the layout for my app
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Adapter(this, customerModel);
        recyclerView.setAdapter(adapter);

        //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

        //ArrayAdapter customerArrayAdapter=new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1,everyone);

        //recyclerView.setAdapter(customerArrayAdapter);

        // Initialize list before checking for new items


        // Receive new item data from Register activity
//        if (getIntent().hasExtra("newCustomer")) {
//            CustomerModel newItem = (CustomerModel) getIntent().getSerializableExtra("newCustomer");
//            if (newItem != null) {
//                // Add the new item to the existing list
//                items.add(newItem);
//            }
//        }
    }

    public void onDeleteContact(int index) {

        customerModel.remove(index);
        adapter.notifyDataSetChanged();

    }

    public void onUpdateContact(int index, String[] values) {
        customerModel.get(index).setEmail(values[0]);
        customerModel.get(index).setPassword(values[1]);
        adapter.notifyDataSetChanged();
    }

}