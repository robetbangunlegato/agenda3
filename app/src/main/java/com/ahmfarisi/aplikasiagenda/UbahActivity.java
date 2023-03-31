package com.ahmfarisi.aplikasiagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {
    private EditText etTanggal, etJam, etKegiatan;
    private Button btnUbah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent terima = getIntent();
        String yId = terima.getStringExtra("xId");
        String yTanggal = terima.getStringExtra("xTanggal");
        String yJam = terima.getStringExtra("xJam");
        String yKegiatan = terima.getStringExtra("xKegiatan");

        etTanggal = findViewById(R.id.et_tanggal);
        etJam = findViewById(R.id.et_jam);
        etKegiatan = findViewById(R.id.et_kegiatan);
        btnUbah = findViewById(R.id.btn_ubah);

        etTanggal.setText(yTanggal);
        etJam.setText(yJam);
        etKegiatan.setText(yKegiatan);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggal, jam, kegiatan;

                tanggal = etTanggal.getText().toString();
                jam = etJam.getText().toString();
                kegiatan = etKegiatan.getText().toString();

                if(tanggal.trim().equals("")){
                    etTanggal.setError("Tanggal Harus Diisi");
                }
                else if(jam.trim().equals("")){
                    etJam.setError("Jam Harus Diisi");
                }
                else if(kegiatan.trim().equals("")){
                    etKegiatan.setError("Kegiatan Harus Diisi");
                }
                else{
                    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);
                    long eks = myDB.ubahAgenda(yId, tanggal, jam, kegiatan);
                    if(eks == -1){
                        Toast.makeText(UbahActivity.this, "Ubah Data Gagal!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(UbahActivity.this, "Ubah Data Sukses!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}