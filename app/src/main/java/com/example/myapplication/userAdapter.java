package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.MyViewHolder> {
    private ArrayList<user> userData;
    private Context context;

    public userAdapter(ArrayList<user> userdata, Context context) {
        this.userData = userdata;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_user, parent, false);

        return new userAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final user model = userData.get(position);
        Log.e("TAG", "data"+userData.get(position).getName() );
        holder.tvName.setText("Name : "+model.getName());
        holder.tvEmail.setText("Email : "+model.getEmail());
        holder.tvGender.setText("Gender : "+model.getGender());
        String status=model.getStatus();
        if(status.equals("Active")) {
            holder.tvStatus.setTextColor(Color.BLUE);
            holder.tvStatus.setText("Status : " + model.getStatus());
        }else
        {
            holder.tvStatus.setTextColor(Color.RED);
            holder.tvStatus.setText("Status : " + model.getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvEmail,tvGender,tvStatus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName =itemView.findViewById(R.id.tvName);
            tvEmail =itemView.findViewById(R.id.tvEmail);
            tvGender =itemView.findViewById(R.id.tvGender);
            tvStatus =itemView.findViewById(R.id.tvStatus);
        }
    }
}
