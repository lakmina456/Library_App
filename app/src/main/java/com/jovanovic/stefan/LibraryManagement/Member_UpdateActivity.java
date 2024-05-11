package com.jovanovic.stefan.LibraryManagement;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Member_UpdateActivity extends AppCompatActivity {

    EditText name_input2, member_address_input2,member_phone_input2,member_unpaid_dues_input2;
    Button update_button,delete_button;

    String id,name, address, phone,unpaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_update);

        name_input2 = findViewById(R.id.name_input2);
        member_address_input2 = findViewById(R.id.member_address_input2);
        member_phone_input2 = findViewById(R.id.member_phone_input2);
        member_unpaid_dues_input2 = findViewById(R.id.member_unpaid_dues_input2);

        update_button = findViewById(R.id.update_button);

        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar address after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(Member_UpdateActivity.this);
                name = name_input2.getText().toString().trim();
                address =  member_address_input2.getText().toString().trim();
                phone =  member_phone_input2.getText().toString().trim();
                unpaid =  member_unpaid_dues_input2.getText().toString().trim();
                myDB.updateData_member(id,name, address,  phone,unpaid);
            }
        });
        
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("CARD_NO") && getIntent().hasExtra("NAME") && getIntent().hasExtra("ADDRESS") &&
                getIntent().hasExtra("PHONE")&&
                getIntent().hasExtra("UNPAID_DUES") ){
            //Getting Data from Intent
            id = getIntent().getStringExtra("CARD_NO");
            name = getIntent().getStringExtra("NAME");
            address = getIntent().getStringExtra("ADDRESS");
            phone = getIntent().getStringExtra("PHONE");
            unpaid = getIntent().getStringExtra("UNPAID_DUES");


            //Setting Intent Data
            name_input2.setText(name);
            member_address_input2.setText( address);
            member_phone_input2.setText( phone);
            member_unpaid_dues_input2.setText( unpaid);
            Log.d("stev", name+" "+address+" "+ phone+""+unpaid);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Member_UpdateActivity.this);
                myDB.deleteOneRow_member(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
