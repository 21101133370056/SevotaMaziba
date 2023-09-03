package com.matalali.sevotamaziba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SevothaDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "USERS_DB";
    //constructors for database
    public SevothaDB(Context context) {
        super(context, "USERS_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USER_TABLE(id integer primary key autoincrement,username TEXT NOT NULL,address TEXT NOT NULL," +
                "student_phone TEXT NOT NULL,parent_phone TEXT NOT NULL,email TEXT NOT NULL,password TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS USER_TABLE");

    }

    public Boolean AddUser(String username,String address,String student_phone,String p_phone,String email,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username",username);
        contentValues.put("address",address);
        contentValues.put("student_phone",student_phone);
        contentValues.put("parent_phone",p_phone);
        contentValues.put("email",email);
        contentValues.put("password",password);

        long result = sqLiteDatabase.insert("USER_TABLE",null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean login(String username,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from USER_TABLE where username=? and password=?",new String[]{username,password});
        if (cursor.getCount()>0) {
            return true;
        }else {
            return false;
        }
    }


}
