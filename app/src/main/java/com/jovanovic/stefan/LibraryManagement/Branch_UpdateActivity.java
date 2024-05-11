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

public class Branch_UpdateActivity extends AppCompatActivity {

    EditText branch_name_input2, branch_address_input2;
    Button update_button,delete_button;

    String id,name, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_update);

        branch_name_input2 = findViewById(R.id.name_input2);
        branch_address_input2 = findViewById(R.id.branch_address_input2);


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
                MyDatabaseHelper myDB = new MyDatabaseHelper(Branch_UpdateActivity.this);
                name = branch_name_input2.getText().toString().trim();
                address =  branch_address_input2.getText().toString().trim();

                myDB.updateData_branch(id,name, address);
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
        if(getIntent().hasExtra("BRANCH_ID") && getIntent().hasExtra("BRANCH_NAME") && getIntent().hasExtra("BRANCH_ADDRESS")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("BRANCH_ID");
            name = getIntent().getStringExtra("BRANCH_NAME");
            address = getIntent().getStringExtra("BRANCH_ADDRESS");



            //Setting Intent Data
            branch_name_input2.setText(name);
            branch_address_input2.setText( address);

            Log.d("stev", name+" "+address);
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
                MyDatabaseHelper myDB = new MyDatabaseHelper(Branch_UpdateActivity.this);
                myDB.deleteOneRow_branch(id);
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
