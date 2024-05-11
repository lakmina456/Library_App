package com.jovanovic.stefan.LibraryManagement;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class Adding_sample extends AppCompatActivity {
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //MyDatabaseHelper mydatabase = new MyDatabaseHelper(Adding_sample.this);
        database = openOrCreateDatabase("BookLibrary.db", MODE_PRIVATE, null);

        // Start a transaction
        database.beginTransaction();

        try {
            String insertQuery = "INSERT INTO Book (TITLE, PUBLISHER_NAME) VALUES " +
                    "('To Kill a Mockingbird', 'HarperCollins')," +
                    "('1984', 'Penguin Books')," +
                    "('The Great Gatsby', 'Scribner')," +
                    "('Pride and Prejudice', 'Penguin Books')," +
                    "('The Catcher in the Rye', 'Little, Brown and Company')";

            // Execute the SQL query to insert data into the "Book" table
            database.execSQL(insertQuery);

            String insertQuery1 = "INSERT INTO Publisher (NAME, ADDRESS, PHONE) VALUES " +
                    "('HarperCollins', '123 Main St, New York', '555-1234')," +
                    "('Penguin Books', '456 Elm St, London', '555-5678')," +
                    "('Scribner', '789 Oak St, Chicago', '555-9012')," +
                    "('Little, Brown and Company', '321 Pine St, Boston', '555-3456')";

            // Execute the SQL query to insert data into the "Publisher" table
            database.execSQL(insertQuery1);
            String insertQuery2 = "INSERT INTO Branch (BRANCH_NAME, BRANCH_ADDRESS) VALUES " +
                    "('Main Branch', '123 Main St, Cityville')," +
                    "('Downtown Branch', '456 Elm St, Metropolis')," +
                    "('Uptown Branch', '789 Oak St, Townsville')," +
                    "('East Branch', '321 Pine St, Villageton')";

            // Execute the SQL query to insert data into the "Branch" table
            database.execSQL(insertQuery2);
            String insertQuery3 = "INSERT INTO Member (NAME, ADDRESS, PHONE) VALUES " +
                    "('John Doe', '123 Main St, Cityville', '555-1234')," +
                    "('Jane Smith', '456 Elm St, Metropolis', '555-5678')," +
                    "('Michael Johnson', '789 Oak St, Townsville', '555-9012')," +
                    "('Emily Brown', '321 Pine St, Villageton', '555-3456')";

            // Execute the SQL query to insert data into the "Member" table
            database.execSQL(insertQuery3);
            String insertQuery4 = "INSERT INTO Book_Author (BOOK_ID, AUTHOR_NAME) VALUES " +
                    "(1, 'Harper Lee')," +
                    "(2, 'George Orwell')," +
                    "(3, 'F. Scott Fitzgerald')," +
                    "(4, 'Jane Austen')," +
                    "(5, 'J.D. Salinger')";

            // Execute the SQL query to insert data into the "Book_Author" table
            database.execSQL(insertQuery4);
            String insertQuery5 = "INSERT INTO Book_Copy (BOOK_ID, BRANCH_ID) VALUES " +
                    "(1, 1)," +
                    "(2, 2)," +
                    "(3, 3)," +
                    "(4, 4)," +
                    "(5, 1)";

            // Execute the SQL query to insert data into the "Book_Copy" table
            database.execSQL(insertQuery5);
            String insertQuery6 = "INSERT INTO Book_Loan (BRANCH_ID, CARD_NO, DATE_OUT, DATE_DUE) VALUES " +
                    "(1, 1, '2024-05-11', '2024-06-11')," +
                    "(2, 2, '2024-05-11', '2024-06-11')," +
                    "(3, 3, '2024-05-11', '2024-06-11')," +
                    "(4, 4, '2024-05-11', '2024-06-11')," +
                    "(1, 2, '2024-05-11', '2024-06-11')";

            // Execute the SQL query to insert data into the "Book_Loan" table
            database.execSQL(insertQuery6);
            
            
            database.setTransactionSuccessful();
        } finally {
            // End the transaction
            database.endTransaction();
        }
    }

}
