package com.example.thuan.myapplication;

public class class_BangGiaVeXe {
     public int _iD;
    public String _hinhThuc ;
    public String _doiTuong ;
    public int _giaVe ;
    public int _chieuDai;

    public class_BangGiaVeXe(int _iD, String _hinhThuc, String _doiTuong, int _giaVe, int _chieuDai) {
        this._iD = _iD;
        this._hinhThuc = _hinhThuc;
        this._doiTuong = _doiTuong;
        this._giaVe = _giaVe;
        this._chieuDai = _chieuDai;
    }

    @Override
    public String toString() {
        return "class_BangGiaVeXe{" +
                "_iD=" + _iD +
                ", _hinhThuc='" + _hinhThuc + '\'' +
                ", _doiTuong='" + _doiTuong + '\'' +
                ", _giaVe=" + _giaVe +
                ", _chieuDai=" + _chieuDai +
                '}';
    }

    public class_BangGiaVeXe(){

    }
}
