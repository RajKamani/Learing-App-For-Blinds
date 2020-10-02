package com.TheVision.tv;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {

    private static final String DATA_BASE = "TheVision";
    private static final int VERSION = 1;
    public static String qry, typeuser, lang1, email, file, wordC;

    private FirebaseUser user;


    public DBhelper(Context context) {
        super(context, DATA_BASE, null, VERSION);
        user = FirebaseAuth.getInstance().getCurrentUser();


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        qry = "create table info_user (user String,type text,lang text)";
        db.execSQL(qry);
        qry = "create table bookmark1 (user String,file String,wordC String)";
        db.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert() {
        if (user != null) {
            email = user.getEmail();
            Log.e("onclickuser", "email: " + email);
        }

        SQLiteDatabase db = getWritableDatabase();
        qry = "insert into info_user values (?,?,?)";
        db.execSQL(qry, new String[]{email, typeuser, lang1});
        Log.e("insert", "success");
        return true;
    }

    public boolean insert2() {
        if (user != null) {
            email = user.getEmail();
            Log.e("onclickuser", "email: " + email);
        }

        SQLiteDatabase db = getWritableDatabase();
        qry = "insert into bookmark1 values (?,?,?)";
        db.execSQL(qry, new String[]{email, file, wordC});
        Log.e("insert", "success");
        return true;
    }

    public ArrayList<String> selectbook() {
        ArrayList<String> arrayList = new ArrayList<String>();

        Cursor c;

        SQLiteDatabase db = getReadableDatabase();
        qry = "select wordC from bookmark1 where user = ?";
        c = db.rawQuery(qry, new String[]{email}, null);
        c.moveToLast();
        while (c.isLast()) {
            arrayList.add(c.getString(0));
            c.moveToNext();
        }

        c.close();
        return arrayList;
    }

    public ArrayList<String> selectbook2() {
        ArrayList<String> arrayList = new ArrayList<String>();

        Cursor c;

        SQLiteDatabase db = getReadableDatabase();
        qry = "select file from bookmark1 where user = ?";
        c = db.rawQuery(qry, new String[]{email});
        c.moveToLast();
        while (c.isLast()) {
            arrayList.add(c.getString(0));
            c.moveToNext();
        }


        return arrayList;
    }


    public ArrayList<String> select() {
        ArrayList<String> arrayList = new ArrayList<String>();

        Cursor c;

        SQLiteDatabase db = getReadableDatabase();
        qry = "select type from info_user where user = ?";
        c = db.rawQuery(qry, new String[]{email});
        c.moveToLast();
        while (c.isLast()) {
            arrayList.add(c.getString(0));
            c.moveToNext();
        }


        return arrayList;
    }

    public ArrayList<String> selectlang() {
        ArrayList<String> arrayList = new ArrayList<String>();

        Cursor c;

        SQLiteDatabase db = getReadableDatabase();
        qry = "select lang from info_user where user = ?";
        c = db.rawQuery(qry, new String[]{email});
        c.moveToLast();
        while (c.isLast()) {
            arrayList.add(c.getString(0));
            c.moveToNext();
        }


        return arrayList;
    }


}
