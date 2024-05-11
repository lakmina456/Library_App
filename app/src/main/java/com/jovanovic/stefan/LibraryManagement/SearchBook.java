package com.jovanovic.stefan.LibraryManagement;
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
import androidx.appcompat.app.AppCompatActivity;

public class SearchBook extends AppCompatActivity {

    EditText searchEditText;
    ListView listView;
    ArrayAdapter<String> adapter;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<String> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbook);

        searchEditText = findViewById(R.id.searchEditText1);
        listView = findViewById(R.id.listView1);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
        listView.setAdapter(adapter);

        // Initialize SQLite Database
        sqLiteDatabase = openOrCreateDatabase("BookLibrary.db", Context.MODE_PRIVATE, null);

        // Call method to fetch all books from the database
        fetchAllBooks();

        // TextWatcher to listen to changes in the searchEditText
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // Filter books based on the text entered in the searchEditText
                filterBooks(editable.toString());
            }
        });
    }

    // Method to fetch all books from the database
    private void fetchAllBooks() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Book", null);
        if (cursor.moveToFirst()) {
            do {
                String bookTitle = cursor.getString(cursor.getColumnIndex("TITLE"));
                bookList.add(bookTitle);
            } while (cursor.moveToNext());
            adapter.notifyDataSetChanged();
        }
        cursor.close();
    }

    // Method to filter books based on the search query
    private void filterBooks(String query) {
        ArrayList<String> filteredList = new ArrayList<>();
        for (String book : bookList) {
            if (book.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(book);
            }
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredList);
        listView.setAdapter(adapter);
    }
}
