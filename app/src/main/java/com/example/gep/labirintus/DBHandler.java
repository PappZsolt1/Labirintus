package com.example.gep.labirintus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gep on 2017.05.10..
 */

public class DBHandler {

    public final static String DB_NAME = "top10";
    public final static int DB_VERSION = 1;
    public final static String TABLE_RECORDS = "records";

    public DBHelper dbHelper;

    public DBHandler(Context context) { dbHelper = new DBHelper(context); }

    public void addRecord(String name, int time, int tries) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("time", time);
        values.put("tries", tries);
        db.insert(TABLE_RECORDS, null, values);
        db.delete(TABLE_RECORDS, "time = (SELECT Max(time) FROM " + TABLE_RECORDS + ")", null);
        db.close();
    }

    public Cursor loadRecords() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RECORDS +
                " ORDER BY time ASC", null);
        cursor.moveToFirst();
        db.close();
        return cursor;
    }



    public class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_RECORDS + "(" +
                    "_id    INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name   VARCHAR(50), " +
                    "time   INTEGER, " +
                    "tries  INTEGER" +
                    ")");

            ContentValues values = new ContentValues();
            values.put("name", "Ismeretlen");
            values.put("time", 10 * 60 * 1000);
            values.put("tries", 100);
            for (int i = 0; i < 10; i++) {
                db.insert(TABLE_RECORDS, null, values);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
            onCreate(db);
        }
    }
}
