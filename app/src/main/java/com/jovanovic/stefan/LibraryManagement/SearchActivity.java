package com.jovanovic.stefan.LibraryManagement;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
public class SearchActivity extends AppCompatActivity {

    EditText searchEditText;
    ListView listView;
    ArrayAdapter<String> adapter;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<String> memberList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEditText = findViewById(R.id.searchEditText);
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, memberList);
        listView.setAdapter(adapter);

        // Initialize SQLite Database
        sqLiteDatabase = openOrCreateDatabase("BookLibrary.db", Context.MODE_PRIVATE, null);

        // Call method to fetch all members from the database
        fetchAllMembers();

        // TextWatcher to listen to changes in the searchEditText
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // Filter members based on the text entered in the searchEditText
                filterMembers(editable.toString());
            }
        });
    }

    // Method to fetch all members from the database
    private void fetchAllMembers() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Member", null);
        if (cursor.moveToFirst()) {
            do {
                String memberName = cursor.getString(cursor.getColumnIndex("NAME"));
                memberList.add(memberName);
            } while (cursor.moveToNext());
            adapter.notifyDataSetChanged();
        }
        cursor.close();
    }

    // Method to filter members based on the search query
    private void filterMembers(String query) {
        ArrayList<String> filteredList = new ArrayList<>();
        for (String member : memberList) {
            if (member.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(member);
            }
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredList);
        listView.setAdapter(adapter);
    }

}
