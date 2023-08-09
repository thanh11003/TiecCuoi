package com.example.tieccuoi.DTO;

public class LoaiMonAn {
    String MaLoai;
    String TenLoaiMonAn;

    public LoaiMonAn(String maLoai, String tenLoaiMonAn) {
        MaLoai = maLoai;
        TenLoaiMonAn = tenLoaiMonAn;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoaiMonAn() {
        return TenLoaiMonAn;
    }

    public void setTenLoaiMonAn(String tenLoaiMonAn) {
        TenLoaiMonAn = tenLoaiMonAn;
    }
}
