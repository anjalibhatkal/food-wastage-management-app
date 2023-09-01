package com.example.food_wastage_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Integer> customerID;
    private ArrayList<String> customerName, customerEmail, customerPhone, customerAddress, customerZipCode, foodTypeValue, foodQuantityValue;
    private OnRecycleClickListener listener;

    public interface OnRecycleClickListener {
        void onRecycleClick(int position);
    }

    public void setOnRecycleClickListener(OnRecycleClickListener clickListener) {
        listener = clickListener;
    }

    CustomAdapter(Context context,
                  ArrayList<Integer> customerID,
                  ArrayList<String> customerName,
                  ArrayList<String> customerEmail,
                  ArrayList<String> customerPhone,
                  ArrayList<String> customerAddress,
                  ArrayList<String> customerZipCode,
                  ArrayList<String> foodTypeValue,
                  ArrayList<String> foodQuantityValue) {
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.custidtxt.setText(String.valueOf(customerID.get(position)));
        holder.custnametxt.setText(customerName.get(position));
        holder.custemailtxt.setText(customerEmail.get(position));
        holder.custphtext.setText(customerPhone.get(position));
        holder.custaddtxt.setText(customerAddress.get(position));
        holder.custziptxt.setText(customerZipCode.get(position));
        holder.foodtypetxt.setText(foodTypeValue.get(position));
        holder.foodqtxt.setText(foodQuantityValue.get(position));
    }

    @Override
    public int getItemCount() {
        return customerID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView custidtxt, custnametxt, custemailtxt, custphtext, custaddtxt, custziptxt, foodtypetxt, foodqtxt;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.recycle_button);
            custidtxt = itemView.findViewById(R.id.food_id);
            custnametxt = itemView.findViewById(R.id.customer_name);
            foodtypetxt = itemView.findViewById(R.id.food_type);
            custaddtxt = itemView.findViewById(R.id.customer_address);
            custphtext = itemView.findViewById(R.id.customer_phone);
            custziptxt = itemView.findViewById(R.id.customer_zipcode);
            custemailtxt = itemView.findViewById(R.id.customer_email);
            foodqtxt = itemView.findViewById(R.id.food_quantity);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onRecycleClick(position);
                        }
                    }
                }
            });
        }
    }
}
