package com.example.tieccuoi.DB;

import android.app.Application;

import com.example.tieccuoi.DTO.MonAn;

import java.io.Serializable;
import java.util.ArrayList;

public class App extends Application implements Serializable {
    public static ArrayList<MonAn> data;

    DBHelper dbHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DBHelper(this);
        dbHelper.CopyDatabaseFromAssets();
    }
}
