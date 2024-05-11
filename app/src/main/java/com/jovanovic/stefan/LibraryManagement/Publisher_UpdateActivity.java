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

public class Publisher_UpdateActivity extends AppCompatActivity {

    EditText name_input2, publisher_address_input2,publisher_phone_input2;
    Button update_button,delete_button;

    String name, address, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher_update);

        name_input2 = findViewById(R.id.name_input2);
        publisher_address_input2 = findViewById(R.id.publisher_address_input2);
        publisher_phone_input2 = findViewById(R.id.publisher_phone_input2);

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
                MyDatabaseHelper myDB = new MyDatabaseHelper(Publisher_UpdateActivity.this);
                name = name_input2.getText().toString().trim();
                address =  publisher_address_input2.getText().toString().trim();
                phone =  publisher_phone_input2.getText().toString().trim();
                myDB.updateData_publisher(name, address,  phone);
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
        if(getIntent().hasExtra("publisher_name") && getIntent().hasExtra("publisher_address") &&
                getIntent().hasExtra("publisher_phone") ){
            //Getting Data from Intent
            name = getIntent().getStringExtra("publisher_name");
            address = getIntent().getStringExtra("publisher_address");
            phone = getIntent().getStringExtra("publisher_phone");


            //Setting Intent Data
            name_input2.setText(name);
            publisher_address_input2.setText( address);
            publisher_phone_input2.setText( phone);
            Log.d("stev", name+" "+address+" "+ phone);
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
                MyDatabaseHelper myDB = new MyDatabaseHelper(Publisher_UpdateActivity.this);
                myDB.deleteOneRow_publisher(name);
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
