package com.jovanovic.stefan.LibraryManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button add_button,publishers_button,branch_button,member_button;
    Button add_books,search_books,register_member,search_member;
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDB  = new MyDatabaseHelper(MainActivity.this);

        ////////////////////////////////


        ////////////////////////////////
        add_button = findViewById(R.id.button_books);
        publishers_button = findViewById(R.id.button_publishers);
        branch_button = findViewById(R.id.button_branches);
        member_button = findViewById(R.id.button_members);
        add_books = findViewById(R.id.add_books);
        search_books = findViewById(R.id.search_books);
        register_member = findViewById(R.id.register_member);
        search_member = findViewById(R.id.search_member);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });

        publishers_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper db = new MyDatabaseHelper(getApplicationContext());

                Intent intent = new Intent(MainActivity.this, PublisherActivity.class);
                startActivity(intent);
            }
        });

        branch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper db = new MyDatabaseHelper(getApplicationContext());

                Intent intent = new Intent(MainActivity.this, BranchActivity.class);
                startActivity(intent);
            }
        });

        member_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper db = new MyDatabaseHelper(getApplicationContext());

                Intent intent = new Intent(MainActivity.this, MemberActivity.class);
                startActivity(intent);
            }
        });
        add_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper db = new MyDatabaseHelper(getApplicationContext());

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        register_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper db = new MyDatabaseHelper(getApplicationContext());

                Intent intent = new Intent(MainActivity.this, Member_AddActivity.class);
                startActivity(intent);
            }
        });
        search_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper db = new MyDatabaseHelper(getApplicationContext());

                Intent intent = new Intent(MainActivity.this, SearchBook.class);
                startActivity(intent);
            }
        });
        search_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper db = new MyDatabaseHelper(getApplicationContext());

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }
}
