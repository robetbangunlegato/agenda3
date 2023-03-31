package com.ahmfarisi.aplikasiagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabTambah;
    private RecyclerView rvAgenda;
    private MyDatabaseHelper myDB;
    private AdapterAgenda adAgenda;
    private ArrayList<String> arrId, arrTanggal, arrJam, arrKegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabTambah = findViewById(R.id.fab_tambah);
        rvAgenda = findViewById(R.id.rv_agenda);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        arrId = new ArrayList<>();
        arrTanggal = new ArrayList<>();
        arrJam = new ArrayList<>();
        arrKegiatan = new ArrayList<>();
    }

    private void tampilAgenda(){
        Cursor varCursor = myDB.bacaDataAgenda();
        if(varCursor.getCount() == 0){
            Toast.makeText(this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        }
        else{
            arrId.clear();
            arrTanggal.clear();
            arrJam.clear();
            arrKegiatan.clear();

            while (varCursor.moveToNext()){
                arrId.add(varCursor.getString(0));
                arrTanggal.add(varCursor.getString(1));
                arrJam.add(varCursor.getString(2));
                arrKegiatan.add(varCursor.getString(3));
            }

            adAgenda = new AdapterAgenda(MainActivity.this, arrId, arrTanggal, arrJam, arrKegiatan);
            rvAgenda.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            rvAgenda.setAdapter(adAgenda);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilAgenda();
    }
}