package com.jovanovic.stefan.LibraryManagement;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;
    ///////////////////////////////////////////
  
    MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qr1 = "CREATE TABLE Book(\n" +
                "    BOOK_ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    TITLE TEXT,\n" +
                "    PUBLISHER_NAME TEXT,\n" +
                "    AVAILABLE INTEGER DEFAULT 1\n" +
                ")";
        db.execSQL(qr1);

        // create Publisher table
        String qr2 = "CREATE TABLE Publisher(\n" +
                "NAME VARCHAR(20),\n" +
                "ADDRESS VARCHAR(30),\n" +
                "PHONE VARCHAR(10),\n" +
                "PRIMARY KEY (NAME));\n";
        db.execSQL(qr2);

        // create Branch table
        String qr3 = "CREATE TABLE Branch(\n" +
                "BRANCH_ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "BRANCH_NAME TEXT,\n" +
                "BRANCH_ADDRESS TEXT);\n";
        db.execSQL(qr3);

        // create Member table
        String qr4 = "CREATE TABLE Member(\n" +
                "CARD_NO INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "NAME TEXT,\n" +
                "ADDRESS TEXT,\n" +
                "PHONE TEXT,\n" +
                "UNPAID_DUES REAL DEFAULT 0);\n";
        db.execSQL(qr4);

        // create Book_Author table
        String qr5 = "CREATE TABLE Book_Author(\n" +
                "BOOK_ID INTEGER,\n" +
                "AUTHOR_NAME TEXT,\n" +
                "PRIMARY KEY(BOOK_ID, AUTHOR_NAME),\n" +
                "FOREIGN KEY(BOOK_ID) REFERENCES Book);\n";
        db.execSQL(qr5);

        // create Book_Copy table
        String qr6 = "CREATE TABLE Book_Copy(\n" +
                "BOOK_ID INTEGER,\n" +
                "BRANCH_ID INTEGER,\n" +
                "ACCESS_NO INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "FOREIGN KEY(BOOK_ID) REFERENCES Book(BOOK_ID),\n" +
                "FOREIGN KEY(BRANCH_ID) REFERENCES Branch(BRANCH_ID));\n";
        db.execSQL(qr6);

        // create Book_Loan table
        String qr7 = "CREATE TABLE Book_Loan(\n" +
                "ACCESS_NO INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "BRANCH_ID INTEGER,\n" +
                "CARD_NO INTEGER,\n" +
                "DATE_OUT TEXT,\n" +
                "DATE_DUE TEXT,\n" +
                "DATE_RETURNED TEXT,\n" +
                "FOREIGN KEY(ACCESS_NO, BRANCH_ID) REFERENCES Book_Copy,\n" +
                "FOREIGN KEY(CARD_NO) REFERENCES Member,\n" +
                "FOREIGN KEY(BRANCH_ID) REFERENCES Branch);\n";
        db.execSQL(qr7);


}

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "Book" );
        onCreate(db);
    }
    public void sample() {
        SQLiteDatabase db = this.getWritableDatabase();


    }

    void addBook(String title, String publisher_name,String available){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("TITLE", title);
        cv.put("PUBLISHER_NAME", publisher_name);
        cv.put("AVAILABLE", available);

        long result = db.insert("Book",null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();


        }
    }

    void addPublisher(String publisher_name,String publisher_address,String publisher_phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("NAME", publisher_name);
        cv.put("ADDRESS", publisher_address);
        cv.put("PHONE", publisher_phone);

        long result = db.insert("Publisher",null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();


        }
    }
    void addBranch(String branch_name,String branch_address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put("BRANCH_NAME", branch_name);
        cv.put("BRANCH_ADDRESS", branch_address);

        long result = db.insert("Branch",null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();


        }
    }
    void addMember(String NAME,String ADDRESS,String PHONE,Double UNPAID_DUES){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put("NAME", NAME);
        cv.put("ADDRESS", ADDRESS);
        cv.put("PHONE", PHONE);
        cv.put("UNPAID_DUES", UNPAID_DUES);

        long result = db.insert("Member",null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();


        }
    }
    void copyBook(String title, String publisher_name,String available){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("TITLE", title);
        cv.put("PUBLISHER_NAME", publisher_name);
        cv.put("AVAILABLE", available);

        long result = db.insert("Book",null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();


        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + "Book";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor readAllData_publisher(){
        String query = "SELECT * FROM " + "Publisher";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor readAllData_branch(){
        String query = "SELECT * FROM " + "Branch";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor readAllData_member(){
        String query = "SELECT * FROM " + "Member";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String title, String publisher_name,String AVAILABLE){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("TITLE", title);
        cv.put("PUBLISHER_NAME", publisher_name);
        cv.put("AVAILABLE", AVAILABLE);

        long result = db.update("Book", cv, "BOOK_ID=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
    void updateData_publisher(String row_name, String address, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", row_name);
        cv.put("ADDRESS", address);
        cv.put("PHONE", phone);


        long result = db.update("Publisher", cv, "NAME=?", new String[]{row_name});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
    void updateData_branch(String row_id, String name, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("BRANCH_NAME", name);
        cv.put("BRANCH_ADDRESS", address);



        long result = db.update("Branch", cv, "BRANCH_ID=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
    void updateData_member(String row_id,String name, String address, String phone,String UNPAID_DUES){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        cv.put("ADDRESS", address);
        cv.put("PHONE", phone);
        cv.put("UNPAID_DUES", UNPAID_DUES);


        long result = db.update("Member", cv, "CARD_NO=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Book", "book_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow_publisher(String row_name){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Publisher", "NAME=?", new String[]{row_name});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow_branch(String row_name){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Branch", "BRANCH_ID=?", new String[]{row_name});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow_member(String row_name){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Member", "CARD_NO=?", new String[]{row_name});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "Book");
    }
    void deleteAllData_publisher(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "Publisher");
    }
    void deleteAllData_branch(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "Branch");
    }
    void deleteAllData_member(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "Member");
    }

}
