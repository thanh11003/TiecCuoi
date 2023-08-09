package com.example.tieccuoi.DB;

public class DBCreateTable_DV {
    public static final String DATABASE_NAME = "DichVu.db";


    //------------------------------------------------------//
    public static final String TABLE_MON_AN = "MonAn";
    public static final String COL_ID_MON_AN = "MaMonAn";
    public static final String COL_NAME_MON_AN = "TenMonAn";
    public static final String COL_PRICE_MON_AN = "DonGiaMonAn";
    public static final String COL_INFO_MON_AN = "ThongTin";

    //------------------------------------------------------//
    public static final String TABLE_GOI_TIEC = "GoiTiec";
    public static final String COL_ID_GOI_TIEC= "MaGoiTiec";
    public static final String COL_INFO_GOI_TIEC = "ThongTinGoiTiec";
    public static final String COL_NAME_GOI_TIEC = "TenGoiTiec";
    public static final String COL_PRICE_GOI_TIEC = "DonGiaGoiTiec";

    //------------------------------------------------------//
    public static final String CREATE_TABLE_MON_AN = "CREATE TABLE " + TABLE_MON_AN + " ("
            + COL_ID_MON_AN + " INTEGER PRIMARY KEY, "
            + COL_NAME_MON_AN + " TEXT, "
            + COL_PRICE_MON_AN + " INT "
            + COL_INFO_MON_AN + " TEXT) ";

    //------------------------------------------------------//
    public static final String CREATE_TABLE_GOI_TIEC = "CREATE TABLE " + TABLE_GOI_TIEC + " ("
            + COL_ID_GOI_TIEC + " INTEGER PRIMARY KEY, "
            + COL_NAME_GOI_TIEC + " TEXT, "
            + COL_INFO_GOI_TIEC + " TEXT, "
            + COL_PRICE_GOI_TIEC + " INT) ";

    //------------------------------------------------------//
    public static final String DROP_TABLE_MON_AN = "DROP TABLE IF EXISTS " + TABLE_MON_AN;
    public static final String DROP_TABLE_GOI_TIEC = "DROP TABLE IF EXISTS " + TABLE_GOI_TIEC;
}
