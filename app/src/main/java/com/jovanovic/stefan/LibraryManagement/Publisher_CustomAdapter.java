package com.jovanovic.stefan.LibraryManagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Publisher_CustomAdapter extends RecyclerView.Adapter<Publisher_CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList publisher_name, publisher_address, publisher_phone;

    Publisher_CustomAdapter(Activity activity, Context context, ArrayList publisher_name, ArrayList publisher_address, ArrayList publisher_phone
                  ){
        this.activity = activity;
        this.context = context;
        this.publisher_name = publisher_name;
        this.publisher_address = publisher_address;
        this.publisher_phone = publisher_phone;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_publisher, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
   public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.publisher_name_txt.setText(String.valueOf(publisher_name.get(position)));
        holder.publisher_address_txt.setText(String.valueOf(publisher_address.get(position)));
        holder.publisher_phone_txt.setText(String.valueOf(publisher_phone.get(position)));

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Publisher_UpdateActivity.class);
                intent.putExtra("publisher_name", String.valueOf(publisher_name.get(position)));
                intent.putExtra("publisher_address", String.valueOf(publisher_address.get(position)));
                intent.putExtra("publisher_phone", String.valueOf(publisher_phone.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return publisher_name.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView publisher_name_txt, publisher_address_txt, publisher_phone_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            publisher_name_txt = itemView.findViewById(R.id.book_id_txt);
            publisher_address_txt = itemView.findViewById(R.id.book_title_txt);
            publisher_phone_txt = itemView.findViewById(R.id.publisher_name_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
