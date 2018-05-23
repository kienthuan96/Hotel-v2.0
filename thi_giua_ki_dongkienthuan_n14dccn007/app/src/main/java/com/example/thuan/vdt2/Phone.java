package com.example.thuan.vdt2;

public class Phone {
    private String tenDT,giaDT;

    public Phone() {
    }

    public Phone(String tenDT, String giaDT) {
        this.tenDT = tenDT;
        this.giaDT = giaDT;
    }

    public Phone(Phone phone){
        this.tenDT=phone.tenDT;
        this.giaDT=phone.giaDT;
    }

    public String getTenDT() {
        return tenDT;
    }

    public void setTenDT(String tenDT) {
        this.tenDT = tenDT;
    }

    public String getgiaDT() {
        return giaDT;
    }

    public void setgiaDT(String maDT) {
        this.giaDT = maDT;
    }
}
