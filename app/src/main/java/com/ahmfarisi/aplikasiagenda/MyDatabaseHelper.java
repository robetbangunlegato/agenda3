package com.ahmfarisi.aplikasiagenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context ctx;
    private static final String DATABASE_NAME = "db_agenda";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tbl_agenda";
    private static final String FIELD_ID = "id";
    private static final String FIELD_TANGGAL = "tanggal";
    private static final String FIELD_JAM = "jam";
    private static final String FIELD_KEGIATAN = "kegiatan";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FIELD_TANGGAL + " VARCHAR(16), " +
                FIELD_JAM + " VARCHAR(5), " +
                FIELD_KEGIATAN + " VARCHAR(150) ); "
                ;

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public long tambahAgenda(String tanggal, String jam, String kegiatan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_TANGGAL, tanggal);
        cv.put(FIELD_JAM, jam);
        cv.put(FIELD_KEGIATAN, kegiatan);

        long eksekusi = db.insert(TABLE_NAME, null, cv);
        return eksekusi;
    }

    public Cursor bacaDataAgenda(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor varCursor = null;
        if(db != null){
            varCursor = db.rawQuery(query, null);
        }

        return varCursor;
    }

    public long hapusAgenda(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long eksekusi = db.delete(TABLE_NAME, "id = ?", new String[]{id});
        return eksekusi;
    }

    public long ubahAgenda(String id, String tanggal, String jam, String kegiatan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_TANGGAL, tanggal);
        cv.put(FIELD_JAM, jam);
        cv.put(FIELD_KEGIATAN, kegiatan);

        long eksekusi = db.update(TABLE_NAME, cv, "id = ?", new String[]{id});
        return eksekusi;
    }


}
