package com.example.foodbank;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User_Adapter extends RecyclerView.Adapter<User_Adapter.MyViewHolder3>{
    Context context2;
    ArrayList<UserList> list2;

    public User_Adapter(Context context, ArrayList<UserList> list2) {
        this.context2 = context;
        this.list2 = list2;
    }

    @NonNull
    @Override
    public User_Adapter.MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context2).inflate(R.layout.user_list,parent,false);
        return new User_Adapter.MyViewHolder3(v);
    }

    @Override
    public void onBindViewHolder(@NonNull User_Adapter.MyViewHolder3 holder, int position) {

        UserList user = list2.get(position);
        holder.Email.setText("USER NAME: "+user.getEmail());
        holder.Fname.setText("FName: "+user.getFname());
        holder.Lname.setText("LName: "+user.getLname());
        holder.Password.setText("Password: "+user.getPassword());

        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.Email.getContext());
                builder.setTitle("CONFIRM REMOVE USER");
                builder.setMessage("DOUBLE CONFIRM?");

                builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
                        ref.child(user.getEmail()).setValue(null);
                        Toast.makeText(holder.Email.getContext(), "DONE", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.Email.getContext(), "CANCEL", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();



            }
        });

    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public static class MyViewHolder3 extends RecyclerView.ViewHolder {
        Button option;
        TextView Email,Fname,Lname,Password;
        public MyViewHolder3(@NonNull View itemView) {
            super(itemView);

            Email=itemView.findViewById(R.id.UserID);
            Fname=itemView.findViewById(R.id.LastName);
            Lname=itemView.findViewById(R.id.FirstName);
            Password=itemView.findViewById(R.id.Password);
            option = (Button)itemView.findViewById(R.id.choice);
        }
    }
}
