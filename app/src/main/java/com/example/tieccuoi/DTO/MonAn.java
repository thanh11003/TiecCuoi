package com.example.tieccuoi.DTO;

import java.io.Serializable;

public class MonAn implements Serializable {
    int MaMonAn;
    String TenMonAn;
    String Gia;
    String Image;
    String ThongTin;
    String loai;

    public MonAn(int maMonAn, String tenMonAn, String gia, String image, String thongTin, String Loai) {
        MaMonAn = maMonAn;
        TenMonAn = tenMonAn;
        Gia = gia;
        Image = image;
        ThongTin = thongTin;
        loai = Loai;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        loai = loai;
    }

    public int getMaMonAn() {
        return MaMonAn;
    }

    public void setMaMonAn(int maMonAn) {
        MaMonAn = maMonAn;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getThongTin() {
        return ThongTin;
    }

    public void setThongTin(String thongTin) {
        ThongTin = thongTin;
    }
}
