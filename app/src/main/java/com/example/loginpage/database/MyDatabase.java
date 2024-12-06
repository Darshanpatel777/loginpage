package com.example.loginpage.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabase extends SQLiteOpenHelper {

    // user all login data , contact date , edit - delete karva mate

    public MyDatabase(Context context) {
        super(context, "mydata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // * unique * keyword  all ma date different aave aena mate
       // String table = "CREATE TABLE user (username text unique, email text, password text)";

        // login username , email password (login user date table "user date")
        String table = "CREATE TABLE user (id integer primary key autoincrement ,username text unique, email text, password text)";
        db.execSQL(table);


        // add contact name,number,email,address p (login user  contact d table "contact date")
        String contact = "CREATE TABLE contact (id integer primary key autoincrement,userid integer ,name text , number text , email text , adress text)";
        db.execSQL(contact);


    }

    //  add contact name,number,email,address return & save
    public Boolean addcontact(int userid, String name, String number, String email, String adress) {
        try {

            String insrt = "INSERT INTO contact (userid,name,number,email,adress) VALUES (" + userid + " , '" + name + "' , '" + number + "' , '" + email + "' , '" + adress + "' )";
            getWritableDatabase().execSQL(insrt);
            return true;
        } catch (Exception exception) {
            return false;
        }

    }

    // login  username , email, password return & save
    public Boolean insertdata(String username, String email, String pass) {
        try {
            String insert = "INSERT INTO user (username , email , password) VALUES ('" + username + "','" + email + "','" + pass + "')";
            getWritableDatabase().execSQL(insert);
            return true;
        } catch (Exception e) {
            Log.e("---cr---", "insertdata: " + e);
            return false;
        }
    }

    //user name , number, add etc......... add kare te show karave
    public Cursor selectcon(int userid) {
        String s = "SELECT * FROM contact WHERE userid = " + userid;
        return getReadableDatabase().rawQuery(s, null);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //user login username , password OK check karava
    public Cursor userlogin(String user, String pass) {

        String select = "SELECT * FROM user WHERE username = '" + user + "' AND password = '" + pass + "'";
        Cursor cr = getReadableDatabase().rawQuery(select, null);

        return cr;
    }


    // contact data edit karava
    public void editdata(String newname, String newnum, int contactid) {

        String update = "UPDATE contact SET name = '" + newname + "' , number = '" + newnum + "' WHERE id = " + contactid;
        getWritableDatabase().execSQL(update);

    }


    // contact data delete karava
    public void deletedata(int contactid) {
        String delete = "DELETE FROM contact WHERE ID = " + contactid;
        getWritableDatabase().execSQL(delete);
    }


    public void searchdata(String Query) {

        String search = "SELECt * FROM contact WHERE name LIKE 'query%'";
        getWritableDatabase().execSQL(search);
    }
}
