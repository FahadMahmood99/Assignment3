package com.example.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper {

        private final String DATABASE_NAME = "customer.db";
        public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
        public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
        public static final String COLUMN_CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD";
        public static final String COLUMN_ID = "ID";

        private final int DATABASE_VERSION = 1;

        Context context;

        MyDatabaseHelper helper;

        SQLiteDatabase db;

    public DatabaseHelper(Context c)
    {
        context = c;
    }

        public void open() {
            helper = new MyDatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
            db = helper.getWritableDatabase();
        }

    public void close()
    {
        db.close();
        helper.close();
    }

    private class MyDatabaseHelper extends SQLiteOpenHelper {

        public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CUSTOMER_EMAIL + " TEXT," + COLUMN_CUSTOMER_PASSWORD + " TEXT)";
            db.execSQL(createTableStatement);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

    }

    public boolean addOne(CustomerModel customerModel) {

        open();

            ContentValues cv = new ContentValues();

            cv.put(COLUMN_CUSTOMER_EMAIL, customerModel.getEmail());
            cv.put(COLUMN_CUSTOMER_PASSWORD, customerModel.getPassword());

            long insert = db.insert(CUSTOMER_TABLE, null, cv);


            if (insert == -1) {
                return false;
            } else {
                return true;
            }
        }

        public List<CustomerModel> getEveryone() {

        open();

            List<CustomerModel> returnList = new ArrayList<>();

            String queryString = "SELECT * FROM " + CUSTOMER_TABLE;

            Cursor cursor = db.rawQuery(queryString, null);

            if (cursor.moveToFirst()) {
                do {
                    int customerID = cursor.getInt(0);
                    String customeremail = cursor.getString(1);
                    String customerpassword = cursor.getString(2);

                    CustomerModel newCustomer = new CustomerModel(customerID, customeremail, customerpassword);
                    returnList.add(newCustomer);

                } while (cursor.moveToNext());
            } else {
            }

            cursor.close();
            db.close();
            return returnList;
        }

    public void deleteContact(int id)
    {
        int rows = db.delete(CUSTOMER_TABLE, COLUMN_ID+"=?", new String[]{id+""});
        if(rows > 0)
        {
            Toast.makeText(context, "Contact deleted successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Contact not deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateContact(int id, String newEmail, String newPassword) {

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUSTOMER_EMAIL, newEmail);
        cv.put(COLUMN_CUSTOMER_PASSWORD, newPassword);

        int rows = db.update(CUSTOMER_TABLE, cv, COLUMN_ID + "=?", new String[]{id + ""});

        if (rows > 0) {
            Toast.makeText(context, "Contact updated successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "Failed to update contact", Toast.LENGTH_SHORT).show();
        }
    }


}
