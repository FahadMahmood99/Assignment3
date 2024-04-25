package com.example.assignment3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{


    Context context;
    ArrayList<CustomerModel> customerModel;
    List<CustomerModel> items;

    public Adapter(Context c, List<CustomerModel> list) {
        context = c;
        customerModel = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.single_info, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.email.setText(customerModel.get(position).getEmail());
        holder.password.setText(customerModel.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        return customerModel.size();
    }

    public void setItems(ArrayList<CustomerModel> items) {
        this.items = items;
        notifyDataSetChanged(); // Notify RecyclerView to refresh
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView email,password;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.email);
            password = itemView.findViewById(R.id.password);
        }
    }

}
