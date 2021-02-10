package com.client.rentalkamera;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rentalkamera.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table penyewa (" +
                "nama text," +
                "alamat text," +
                "no_hp text," +
                "primary key(nama)" +
                ");" +
                "");
        db.execSQL("create table kamera(" +
                "merk text," +
                "harga int," +
                "primary key(merk)" +
                ");" +
                "");
        db.execSQL("create table sewa(" +
                "merk text," +
                "nama text," +
                "promo int," +
                "lama int," +
                "total double," +
                "foreign key(merk) references kamera (merk), " +
                "foreign key(nama) references penyewa (nama) " +
                ");" +
                "");

        db.execSQL("insert into kamera values (" +
                "'Fujifilm X100V'," +
                "480000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Sony A6100'," +
                "490000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Nikon D3500'," +
                "420000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Sony A7S III'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Sony ZV-1'," +
                "500000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Canon EOS R5'," +
                "550000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'GoPro Hero 9 Black'," +
                "550000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Olympus OM-D E-M5 Mark III'," +
                "700000" +
                ");" +
                "");
        db.execSQL("insert into kamera values (" +
                "'Fujifilm GFX 100'," +
                "1500000" +
                ");" +
                "");
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<String>();
        String selectQuery = "select * from kamera";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categories;
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}