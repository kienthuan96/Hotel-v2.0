package com.example.thuan.myapplication;

/**
 * Created by Thuan on 3/22/2018.
 */

public class Bus {
    private String maXe, tenXe, danhsachDi, danhsachVe;

    public Bus() {
    }

    public Bus(String maXe, String tenXe) {
        this.maXe = maXe;
        this.tenXe = tenXe;
    }

    public Bus(String maXe, String tenXe, String danhsachDi, String danhsachVe) {
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.danhsachDi = danhsachDi;
        this.danhsachVe = danhsachVe;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }
}
