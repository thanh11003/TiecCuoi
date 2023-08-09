package com.example.tieccuoi.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tieccuoi.DTO.LoaiMonAn;
import com.example.tieccuoi.DTO.MonAn;
import com.example.tieccuoi.DTO.NhanVien;
import com.example.tieccuoi.DTO.Sanh;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper {
    String DB_NAME = "TiecCuoi.db";
    SQLiteDatabase db;
    Context context;
    DBDichVu dbDichVu;


    public DBHelper(Context context) {
        this.context = context;
    }

    public void DatabaseHelper(Context context) {
        dbDichVu = new DBDichVu(context);
        db = dbDichVu.getWritableDatabase();
    }

    public SQLiteDatabase openDB() {
        return context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    }

    public void CopyDatabaseFromAssets() {
        File dbFile = context.getDatabasePath(DB_NAME);
        if (!dbFile.exists()) {
            try {
                InputStream is = context.getAssets().open(DB_NAME);
                OutputStream os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[1024];
                while (is.read(buffer) > 0) {
                    os.write(buffer);
                }

                os.flush();
                os.close();
                is.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<MonAn> getMonAns() {
        ArrayList<MonAn> tmp = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM MonAn";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int mamonan = cursor.getInt(0);
            String tenmon = cursor.getString(1);
            String gia = cursor.getString(2);
            String image = cursor.getString(3);
            String thongtin = cursor.getString(4);
            String maloai = cursor.getString(5);

            MonAn monAn = new MonAn(mamonan, tenmon, gia, image, thongtin, maloai);
            tmp.add(monAn);
        }

        db.close();

        return tmp;
    }

    public long ThemMonAn(MonAn monAn) {
        db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenMonAn", monAn.getTenMonAn());
        contentValues.put("Gia", monAn.getGia());
        contentValues.put("Image", monAn.getImage());
        contentValues.put("ThongTin", monAn.getThongTin());
        long tmp = db.insert("MonAn", "", contentValues);
        db.close();
        return tmp;
    }

    public long SuaMonAn(MonAn monAn) {
        db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenMonAn", monAn.getTenMonAn());
        contentValues.put("Gia", monAn.getGia());
        contentValues.put("Image", monAn.getImage());
        contentValues.put("ThongTin", monAn.getThongTin());
        long tmp = db.update("MonAn", contentValues, "MaMonAn = " + monAn.getMaMonAn(), null);
        db.close();
        return tmp;
    }

    public long XoaMonAn(MonAn monAn) {
        db = openDB();
        long tmp = db.delete("MonAn", "MaMonAn = " + monAn.getMaMonAn(), null);
        db.close();
        return tmp;
    }

    public ArrayList<MonAn> GetInfo() {
        ArrayList<MonAn> tmp = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Contact WHERE ID =?";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int mamonan = cursor.getInt(0);
            String tenmonan = cursor.getString(1);
            String gia = cursor.getString(2);
            String image = cursor.getString(3);
            String thongtin = cursor.getString(4);
            String loai = cursor.getString(5);

            MonAn monAn = new MonAn(mamonan, tenmonan, gia, image, thongtin, loai);
            tmp.add(monAn);
        }

        db.close();

        return tmp;
    }

    public ArrayList<MonAn> SearchMonAn() {
        ArrayList<MonAn> tmp = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Contact WHERE TenMonAn like '%' ";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int mamonan = cursor.getInt(0);
            String tenmonan = cursor.getString(1);
            String gia = cursor.getString(2);
            String image = cursor.getString(3);
            String thongtin = cursor.getString(4);
            String loai = cursor.getString(5);

            MonAn monAn = new MonAn(mamonan, tenmonan, gia, image, thongtin, loai);
            tmp.add(monAn);
        }

        db.close();

        return tmp;
    }


    public ArrayList<LoaiMonAn> getLoai() {
        ArrayList<LoaiMonAn> listLoai = new ArrayList<>();
        db = openDB();
        String sql = "Select * FROM LoaiMonAn";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String MaLoai = cursor.getString(0);
            String TenLoai = cursor.getString(1);

            LoaiMonAn loaiMonAn = new LoaiMonAn(MaLoai, TenLoai);
            listLoai.add(loaiMonAn);
        }
        db.close();
        return listLoai;
    }

    public String getLoaiMonAn(String loai){
        String Loai = null;
        db = openDB();
        String sql = "Select TenLoai From LoaiMonAn Where MaLoai = "  + "  ' " + loai + " ' ";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            String TenLoai = cursor.getString(1);
            loai = TenLoai;
        }
        db.close();;
        return Loai;
    }

//    public NhanVien selectID(String ID) {
//        NhanVien nv;
//        db = openDB();
//        String sql = "Select * from NhanVien where";
//
//                return nv;
//    }

    public ArrayList<Sanh> getSanh(){
        ArrayList<Sanh> tmp = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanh";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int masanh = cursor.getInt(0);
            String tensanh = cursor.getString(1);
            String gia = cursor.getString(2);
            String image = cursor.getString(3);
            String soluongban = cursor.getString(4);
            String dongiaban = cursor.getString(5);
            String thongtin = cursor.getString(6);

            Sanh sanh = new Sanh(masanh, tensanh, gia, image,soluongban,dongiaban ,thongtin);
            tmp.add(sanh);
        }

        db.close();

        return tmp;
    }

    public long ThemSanh(Sanh sanh) {
        db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenSanh", sanh.getTenSanh());
        contentValues.put("Gia", sanh.getGia());
        contentValues.put("Image", sanh.getImage());
        contentValues.put("SoLuongBanTD", sanh.getSoLuongBanTD());
        contentValues.put("DonGiaBan", sanh.getDonGiaBan());
        contentValues.put("Thongtin", sanh.getThongTin());
        long tmp = db.insert("Sanh", "", contentValues);
        db.close();
        return tmp;
    }

    public long SuaSanh(Sanh sanh) {
        db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenSanh", sanh.getTenSanh());
        contentValues.put("Gia", sanh.getGia());
        contentValues.put("Image", sanh.getImage());
        contentValues.put("SoLuongBanTD", sanh.getSoLuongBanTD());
        contentValues.put("DonGiaBan", sanh.getDonGiaBan());
        contentValues.put("Thongtin", sanh.getThongTin());
        long tmp = db.update("Sanh", contentValues, "MaSanh = " + sanh.getId(), null);
        db.close();
        return tmp;
    }

    public long XoaSanh(Sanh sanh) {
        db = openDB();
        long tmp = db.delete("Sanh", "MaSanh = " + sanh.getId(), null);
        db.close();
        return tmp;
    }

    public NhanVien selectbID(String tdn){
        NhanVien nv = null;
        db = openDB();
        String sql = "Select * From NhanVien Where TenDangNhap = " + tdn ;
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tennv = cursor.getString(1);
            String ngaysinh = cursor.getString(2);
            String gioitinh = cursor.getString(3);
            String chucvu = cursor.getString(4);
            String tendangnhap = cursor.getString(5);
            String matkhau = cursor.getString(6);

            nv = new NhanVien(id, tennv, ngaysinh, gioitinh, chucvu, tendangnhap, matkhau);
        }
        return nv;
    }
}
