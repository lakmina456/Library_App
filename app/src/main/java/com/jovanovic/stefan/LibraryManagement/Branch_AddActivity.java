package com.jovanovic.stefan.LibraryManagement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Branch_AddActivity extends AppCompatActivity {

    EditText name_input, address_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_add);

        name_input = findViewById(R.id.name_input);
        address_input = findViewById(R.id.address_input);


        add_button = findViewById(R.id.add_button);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Branch_AddActivity.this);
                myDB.addBranch(name_input.getText().toString().trim(), address_input.getText().toString().trim());

            }
        });

    }
}
