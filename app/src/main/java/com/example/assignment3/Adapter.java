package com.example.assignment3;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    MainActivity parentActivity;
    Context context;
    ArrayList<CustomerModel> customerModel;

    public interface DeleteContact
    {
        public void onDeleteContact(int index);
        public void onUpdateContact(int index, String []values);
    }


    public Adapter(Context c, List<CustomerModel> list) {
        context = c;
        customerModel = new ArrayList<>(list);
        parentActivity = (MainActivity) c;
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


        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog editDialog = new AlertDialog.Builder(context).create();
                editDialog.setTitle("Edit Contact");
                View view = LayoutInflater.from(context).inflate(R.layout.edit_contact_layout, null,false);
                editDialog.setView(view);
                editDialog.show();

                // hooks for dialog view
                EditText newEmail,newPassword;

                Button btnEditContact, btnCancel;

                newEmail = view.findViewById(R.id.newEmail);
                newPassword = view.findViewById(R.id.newPassword);

                btnEditContact = view.findViewById(R.id.btnUpdate);
                btnCancel = view.findViewById(R.id.btnCancel);


                  newEmail.setText(customerModel.get(holder.getAdapterPosition()).getEmail());
                  newPassword.setText(customerModel.get(holder.getAdapterPosition()).getPassword());

                btnEditContact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseHelper database = new DatabaseHelper(context);
                        database.open();
                        database.updateContact(customerModel.get(holder.getAdapterPosition()).getId(),
                                newEmail.getText().toString().trim(),
                                newPassword.getText().toString().trim());
                        database.close();
                        String []updateContact = new String[]{newEmail.getText().toString().trim(),
                                newPassword.getText().toString().trim()};
                        parentActivity.onUpdateContact(holder.getAdapterPosition(), updateContact);
                        editDialog.dismiss();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editDialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return customerModel.size();
    }

//    public void setItems(ArrayList<CustomerModel> items) {
//        this.items = items;
//        notifyDataSetChanged(); // Notify RecyclerView to refresh
//    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView email,password;

        ImageView ivEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.email);
            password = itemView.findViewById(R.id.password);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }

}
