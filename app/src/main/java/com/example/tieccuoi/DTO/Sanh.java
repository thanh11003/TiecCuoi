package com.example.tieccuoi.DTO;

import java.io.Serializable;

public class Sanh implements Serializable {
    int id;
    String TenSanh;
    String Gia;
    String Image;
    String SoLuongBanTD;
    String DonGiaBan;
    String ThongTin;

    public Sanh(int id, String tenSanh, String gia, String image, String soLuongBanTD, String donGiaBan, String thongTin) {
        this.id = id;
        TenSanh = tenSanh;
        Gia = gia;
        Image = image;
        SoLuongBanTD = soLuongBanTD;
        DonGiaBan = donGiaBan;
        ThongTin = thongTin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanh() {
        return TenSanh;
    }

    public void setTenSanh(String tenSanh) {
        TenSanh = tenSanh;
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

    public String getSoLuongBanTD() {
        return SoLuongBanTD;
    }

    public void setSoLuongBanTD(String soLuongBanTD) {
        SoLuongBanTD = soLuongBanTD;
    }

    public String getDonGiaBan() {
        return DonGiaBan;
    }

    public void setDonGiaBan(String donGiaBan) {
        DonGiaBan = donGiaBan;
    }

    public String getThongTin() {
        return ThongTin;
    }

    public void setThongTin(String thongTin) {
        ThongTin = thongTin;
    }
}
