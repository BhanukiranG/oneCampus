package com.example.onecampus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class projectAdapter extends  RecyclerView.Adapter<projectAdapter.ViewHolder>{

    ArrayList<projectModel> list;
    Context context;

    public projectAdapter(ArrayList<projectModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        projectModel model=list.get(position);
        Picasso.get().load(model.getProductImage()).placeholder(R.drawable.mobilelogin).into(holder.itemEventImage);
        holder.itemTitle.setText(model.getTitle());
        holder.itemDiscription.setText(model.getDescription());
        holder.itemClg.setText(model.getClg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,eventDetails.class);
                intent.putExtra("singleImage",model.getProductImage());
                intent.putExtra("singleEventName",model.getTitle());
                intent.putExtra("singleCollege",model.getClg());
                intent.putExtra("singleDiscription",model.getDescription());
                intent.putExtra("singleLink",model.getLink());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemTitle,itemDiscription,itemClg;
        ImageView itemEventImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle=itemView.findViewById(R.id.itemTitle);
            itemDiscription=itemView.findViewById(R.id.itemDiscription);
            itemClg=itemView.findViewById(R.id.itemClg);
            itemEventImage=itemView.findViewById(R.id.itemEventImage);
        }
    }
}
