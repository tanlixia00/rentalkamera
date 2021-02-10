package com.client.rentalkamera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DetailKameraAct extends AppCompatActivity {

    protected Cursor cursor;
    String sMerk, sHarga, sGambar;
    DataHelper dbHelper; //isi database

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kamera);

        Bundle terima = getIntent().getExtras();

        dbHelper = new DataHelper(this);
        Intent intent = getIntent();

        String merk = terima.getString("merk");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from kamera where merk = '" + merk + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            sMerk = cursor.getString(0);
            sHarga = cursor.getString(1);
        }

        if (sMerk.equals("Fujifilm X100V")) {
            sGambar = "fujifilm";
        } else if (sMerk.equals("Sony A6100")) {
            sGambar = "sony";
        } else if (sMerk.equals("Nikon D3500")) {
            sGambar = "nikond3500";
        } else if (sMerk.equals("Sony A7S III")) {
            sGambar = "sonya7s";
        } else if (sMerk.equals("Sony ZV-1")) {
            sGambar = "sonyzv1";
        } else if (sMerk.equals("Canon EOS R5")) {
            sGambar = "canon";
        } else if (sMerk.equals("GoPro Hero 9 Black")) {
            sGambar = "gopro";
        } else if (sMerk.equals("Olympus OM-D E-M5 Mark III")) {
            sGambar = "olympus";
        } else if (sMerk.equals("Fujifilm GFX 100")) {
            sGambar = "fujifilmgfx";
        }

        ImageView ivGambar = findViewById(R.id.ivKamera);
        TextView tvMerk = findViewById(R.id.JKamera);
        TextView tvHarga = findViewById(R.id.JHarga);

        tvMerk.setText(sMerk);
        ivGambar.setImageResource(getResources().getIdentifier(sGambar, "drawable", getPackageName()));

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        tvHarga.setText(kursIndonesia.format(Double.parseDouble(sHarga)));

        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbDetailKmr);
        toolbar.setTitle("Detail Kamera");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}