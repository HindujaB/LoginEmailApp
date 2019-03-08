package com.example.hindu.tha2_app_154010j;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "THA2_DB.db";
    public static final String TABLE_NAME = "User_Infor";
    public static final String COL_1 = "name";
    public static final String COL_2 = "index_no";
    public static final String COL_3 = "email";
    public static final String COL_4 = "mobile";
    public static final String COL_5 = "GPA";
    public static final String COL_6 = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (name TEXT,index_no TEXT PRIMARY KEY,email TEXT,mobile TEXT,GPA TEXT,password TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public User getUser(String index){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {DatabaseHelper.COL_1,DatabaseHelper.COL_2,DatabaseHelper.COL_3,DatabaseHelper.COL_4,DatabaseHelper.COL_5,DatabaseHelper.COL_6};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,columns,COL_2 + "=?", new String[] {index},null,null,null);
        if (cursor != null) cursor.moveToFirst();
        String name = cursor.getString(0);
        String in = cursor.getString(1);
        String mail = cursor.getString(2);
        String mobile = cursor.getString(3);
        String gpa = cursor.getString(4);
        String password = cursor.getString(5);

        User user = new User(name,in,mail,mobile,gpa,password);
        return user;
    }

    public boolean addInfo(String name, String index, String mail, String mobile, String GPA, String password ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,index);
        contentValues.put(COL_3,mail);
        contentValues.put(COL_4,mobile);
        contentValues.put(COL_5,GPA);
        contentValues.put(COL_6,password);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result ==-1) {
            return false;
        }
        else {
            return true;
        }



    }

}
