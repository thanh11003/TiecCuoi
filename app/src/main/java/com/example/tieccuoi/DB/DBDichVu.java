package com.example.tieccuoi.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBDichVu extends SQLiteOpenHelper {
    public DBDichVu(@Nullable Context context) {
        super(context,DBCreateTable_DV.DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBCreateTable_DV.CREATE_TABLE_MON_AN);
        sqLiteDatabase.execSQL(DBCreateTable_DV.CREATE_TABLE_GOI_TIEC);
        sqLiteDatabase.execSQL("insert into MonAn values(null,'Gà Rán',80000,'tomhum.png','')");
        sqLiteDatabase.execSQL("insert into MonAn values(null,'Beefsteak',165000,'tomhum.png','')");
        sqLiteDatabase.execSQL("insert into MonAn values(null,'Tôm Hùm',500000,'tomhum.png','')");
        sqLiteDatabase.execSQL("insert into MonAn values(null,'Gỏi Gà',120000,'tomhum.png','')");
        sqLiteDatabase.execSQL("insert into MonAn values(null,'Mực Nướng Sa Tế',145000,'tomhum.png','')");
        sqLiteDatabase.execSQL("insert into MonAn values(null,'Tôm Nướng',110000,'tomhum.png','')");
        sqLiteDatabase.execSQL("insert into DichVu values(null,'Gói Tiệc 1','Gói Tiệc 1',200000)");
        sqLiteDatabase.execSQL("insert into DichVu values(null,'Gói Tiệc 2','Gói Tiệc 2',200000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DBCreateTable_DV.DROP_TABLE_MON_AN);
        sqLiteDatabase.execSQL(DBCreateTable_DV.DROP_TABLE_GOI_TIEC);
    }
}
