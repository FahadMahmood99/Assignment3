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

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
    public static final String COLUMN_CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "customer.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement= "CREATE TABLE " + CUSTOMER_TABLE+ "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CUSTOMER_EMAIL + " TEXT," + COLUMN_CUSTOMER_PASSWORD + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean addOne(CustomerModel customerModel){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_EMAIL,customerModel.getEmail());
        cv.put(COLUMN_CUSTOMER_PASSWORD,customerModel.getPassword());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);

        if(insert== -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public List<CustomerModel> getEveryone()
    {
        List<CustomerModel> returnList =new ArrayList<>();

        String queryString="SELECT * FROM " + CUSTOMER_TABLE;

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                int customerID=cursor.getInt(0);
                String customeremail=cursor.getString(1);
                String customerpassword=cursor.getString(2);

                CustomerModel newCustomer= new CustomerModel(customerID,customeremail,customerpassword);
                returnList.add(newCustomer);

            }while(cursor.moveToNext());
        }
        else{
        }

        cursor.close();
        db.close();
        return returnList;
    }

}
