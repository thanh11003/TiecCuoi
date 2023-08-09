package com.example.tieccuoi.DTO;

import java.io.Serializable;

public class ChucVu implements Serializable {
    String MaCV;
    String TenCV;

    public ChucVu(String maCV, String tenCV) {
        MaCV = maCV;
        TenCV = tenCV;
    }

    public String getMaCV() {
        return MaCV;
    }

    public void setMaCV(String maCV) {
        MaCV = maCV;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String tenCV) {
        TenCV = tenCV;
    }
}
