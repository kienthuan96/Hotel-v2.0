package com.example.thuan.myapplication;

public class class_BangGiaVeXeMienPhi {
    public  int iDMP ;
    public String tenMP ;
    public String moTaMP ;

    @Override
    public String toString() {
        return "class_BangGiaVeXeMienPhi{" +
                "iDMP=" + iDMP +
                ", tenMP='" + tenMP + '\'' +
                ", moTaMP='" + moTaMP + '\'' +
                '}';
    }

    public class_BangGiaVeXeMienPhi(int iDMP, String tenMP, String moTaMP) {
        this.iDMP = iDMP;
        this.tenMP = tenMP;
        this.moTaMP = moTaMP;
    }

    public class_BangGiaVeXeMienPhi(){

    }

}
