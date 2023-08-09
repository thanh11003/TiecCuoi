package com.example.tieccuoi.DTO;

import java.io.Serializable;

public class NhanVien implements Serializable {
    int MaNV;
    String TenNV;
    String NgaySinh;
    String GioiTinh;
    String ChucVu;
    String TenDangNhap;
    String MatKhau;

    public NhanVien(int maNV, String tenNV, String ngaySinh, String gioiTinh, String chucVu, String tenDangNhap, String matKhau) {
        MaNV = maNV;
        TenNV = tenNV;
        NgaySinh = ngaySinh;
        GioiTinh = gioiTinh;
        ChucVu = chucVu;
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
}
