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

public class Member_CustomAdapter extends RecyclerView.Adapter<Member_CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList card_no,member_name, member_address, member_phone,member_unpaid;

    Member_CustomAdapter(Activity activity, Context context, ArrayList card_no,ArrayList member_name, ArrayList member_address, ArrayList member_phone, ArrayList member_unpaid){
        this.activity = activity;
        this.context = context;
        this.card_no = card_no;
        this.member_name = member_name;
        this.member_address = member_address;
        this.member_phone = member_phone;
        this.member_unpaid = member_unpaid;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_member, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
   public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.card_no_txt.setText(String.valueOf(card_no.get(position)));
        holder.member_name_txt.setText(String.valueOf(member_name.get(position)));
        holder.member_address_txt.setText(String.valueOf(member_address.get(position)));
        holder.member_phone_txt.setText(String.valueOf(member_phone.get(position)));
        holder.member_unpaid_txt.setText(String.valueOf(member_unpaid.get(position)));

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Member_UpdateActivity.class);
                intent.putExtra("CARD_NO", String.valueOf(card_no.get(position)));
                intent.putExtra("NAME", String.valueOf(member_name.get(position)));
                intent.putExtra("ADDRESS", String.valueOf(member_address.get(position)));
                intent.putExtra("PHONE", String.valueOf(member_phone.get(position)));
                intent.putExtra("UNPAID_DUES", String.valueOf(member_unpaid.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {

        return member_name.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView card_no_txt,member_name_txt, member_address_txt, member_phone_txt,member_unpaid_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_no_txt = itemView.findViewById(R.id.card_no_txt);
            member_name_txt = itemView.findViewById(R.id.member_name_txt);
            member_address_txt = itemView.findViewById(R.id.member_address_txt);
            member_phone_txt = itemView.findViewById(R.id.member_phone_txt);
            member_unpaid_txt = itemView.findViewById(R.id.member_unpaid_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
