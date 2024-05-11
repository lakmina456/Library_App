package com.jovanovic.stefan.LibraryManagement;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, publisher_name_input,book_available_input2;
    Button update_button, copy_book,delete_button;

    String book_id, title, publisher_name,book_available;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        publisher_name_input = findViewById(R.id.publisher_name_input2);
        book_available_input2 = findViewById(R.id.book_available_input2);

        update_button = findViewById(R.id.update_button);
           copy_book = findViewById(R.id.copy_book);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                publisher_name =  publisher_name_input.getText().toString().trim();
                book_available =  book_available_input2.getText().toString().trim();
                myDB.updateData(book_id, title,  publisher_name, book_available);
            }
        });
        copy_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                publisher_name =  publisher_name_input.getText().toString().trim();
                book_available =  book_available_input2.getText().toString().trim();
                myDB.copyBook(title,publisher_name,book_available);
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
        if(getIntent().hasExtra("BOOK_ID") && getIntent().hasExtra("TITLE") &&
                getIntent().hasExtra("PUBLISHER_NAME") &&
                getIntent().hasExtra("AVAILABLE")){
            //Getting Data from Intent
            book_id = getIntent().getStringExtra("BOOK_ID");
            title = getIntent().getStringExtra("TITLE");
            publisher_name = getIntent().getStringExtra("PUBLISHER_NAME");
            book_available = getIntent().getStringExtra("AVAILABLE");

            //Setting Intent Data
            title_input.setText(title);
            publisher_name_input.setText( publisher_name);
            book_available_input2.setText( book_available);
            Log.d("stev", title+" "+ publisher_name+""+book_available);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(book_id);
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
