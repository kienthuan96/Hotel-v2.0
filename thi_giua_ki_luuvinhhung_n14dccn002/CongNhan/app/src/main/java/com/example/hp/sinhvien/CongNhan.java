package com.example.hp.sinhvien;

/**
 * Created by VINHHUNG-PC on 4/15/2018.
 */

public class CongNhan {
    private int id;
    private String HoTen;

    public CongNhan(int id, String hoTen) {
        this.id = id;
        HoTen = hoTen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }
}
