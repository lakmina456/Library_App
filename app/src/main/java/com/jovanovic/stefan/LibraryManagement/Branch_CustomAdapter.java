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

public class Branch_CustomAdapter extends RecyclerView.Adapter<Branch_CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList branch_id,branch_name, branch_address;

    Branch_CustomAdapter(Activity activity, Context context, ArrayList branch_id,ArrayList branch_name, ArrayList branch_address){
        this.activity = activity;
        this.context = context;
        this.branch_id= branch_id;
        this.branch_name = branch_name;
        this.branch_address = branch_address;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_branch, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
   public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.branch_id_txt.setText(String.valueOf(branch_id.get(position)));
        holder.branch_name_txt.setText(String.valueOf(branch_name.get(position)));
        holder.branch_address_txt.setText(String.valueOf(branch_address.get(position)));


        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Branch_UpdateActivity.class);
                intent.putExtra("BRANCH_ID", String.valueOf(branch_name.get(position)));
                intent.putExtra("BRANCH_NAME", String.valueOf(branch_name.get(position)));
                intent.putExtra("BRANCH_ADDRESS", String.valueOf(branch_address.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return branch_name.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView branch_id_txt,branch_name_txt, branch_address_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            branch_id_txt = itemView.findViewById(R.id.branch_id_txt);
            branch_name_txt = itemView.findViewById(R.id.branch_name_txt);
            branch_address_txt = itemView.findViewById(R.id.branch_address_txt);


            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
