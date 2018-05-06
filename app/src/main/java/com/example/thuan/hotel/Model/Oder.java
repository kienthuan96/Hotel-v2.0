package com.example.thuan.hotel.Model;

public class Oder {
    private String name_hotel;
    private String address_hotel;
    private  String name;
    private  String email;

    public String getName_hotel() {
        return name_hotel;
    }

    public void setName_hotel(String name_hotel) {
        this.name_hotel = name_hotel;
    }

    public String getAddress_hotel() {
        return address_hotel;
    }

    public void setAddress_hotel(String address_hotel) {
        this.address_hotel = address_hotel;
    }

    private String phone;
    private String DateStartOrder;
    private String DateEndOrder;
    private  int RoomOrder;
    private float totalMoney;

    public Oder() {
    }

    public Oder(String name_hotel, String address_hotel, String name,  String phone, String dateStartOrder, String dateEndOrder, int roomOrder, float totalMoney) {
        this.name_hotel = name_hotel;
        this.address_hotel = address_hotel;
        this.name = name;
        this.phone = phone;
        DateStartOrder = dateStartOrder;
        DateEndOrder = dateEndOrder;
        RoomOrder = roomOrder;
        this.totalMoney = totalMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateStartOrder() {
        return DateStartOrder;
    }

    public void setDateStartOrder(String dateStartOrder) {
        DateStartOrder = dateStartOrder;
    }

    public String getDateEndOrder() {
        return DateEndOrder;
    }

    public void setDateEndOrder(String dateEndOrder) {
        DateEndOrder = dateEndOrder;
    }

    public int getRoomOrder() {
        return RoomOrder;
    }

    public void setRoomOrder(int roomOrder) {
        RoomOrder = roomOrder;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }
}
