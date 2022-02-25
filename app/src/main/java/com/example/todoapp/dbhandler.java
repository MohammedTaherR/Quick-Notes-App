package com.example.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbhandler extends SQLiteOpenHelper {
    public dbhandler( Context context ) {
        super(context, "DB_NAME", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  userlist(list TEXT PRIMARY KEY)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists userlist");
    }
    public Boolean addlist(String list){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("list",list);
        Long res=  db.insert("userlist",null,values);
        if(res==-1) {
            return false;
        }else {
            return true;
        } }
    public void delete (String list){
        SQLiteDatabase db = this.getWritableDatabase();
         db.delete("userlist", "list=?", new String[]{list});
         db.close();
    }
    public Cursor getdata (){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM userlist" ,null);
        return  cursor;

    }}
