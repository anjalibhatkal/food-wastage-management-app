package com.example.food_wastage_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList customerID, customerName, customerEmail, customerPhone, customerAddress, customerZipCode,  foodTypeValue,  foodQuantityValue;

    CustomAdapter(Context context,
                  ArrayList customerID,
                  ArrayList customerName,
                  ArrayList customerEmail,
                  ArrayList customerPhone,
                  ArrayList customerAddress,
                  ArrayList customerZipCode,
                  ArrayList foodTypeValue,
                  ArrayList foodQuantityValue ) {
        this.context = context;
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.customerZipCode = customerZipCode;
        this.foodTypeValue = foodTypeValue;
        this.foodQuantityValue = foodQuantityValue;
    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.custidtxt.setText(String.valueOf(customerID.get(position)));
        holder.custnametxt.setText(String.valueOf(customerName.get(position)));
        holder.custemailtxt.setText(String.valueOf(customerEmail.get(position)));
        holder.custphtext.setText(String.valueOf(customerPhone.get(position)));
        holder.custaddtxt.setText(String.valueOf(customerAddress.get(position)));
        holder.custziptxt.setText(String.valueOf(customerZipCode.get(position)));
        holder.foodtypetxt.setText(String.valueOf(foodTypeValue.get(position)));
        holder.foodqtxt.setText(String.valueOf(foodQuantityValue.get(position)));
    }



    @Override
    public int getItemCount() {
        return customerID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView custidtxt, custnametxt, custemailtxt, custphtext, custaddtxt, custziptxt, foodtypetxt, foodqtxt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            custidtxt = itemView.findViewById(R.id.food_id);
            custnametxt = itemView.findViewById(R.id.customer_name);
            foodtypetxt = itemView.findViewById(R.id.food_type);
            custaddtxt = itemView.findViewById(R.id.customer_address);
            custphtext = itemView.findViewById(R.id.customer_phone);
            custziptxt = itemView.findViewById(R.id.customer_zipcode);
            custemailtxt = itemView.findViewById(R.id.customer_email);
            foodqtxt = itemView.findViewById(R.id.food_quantity);
        }
    }
}
