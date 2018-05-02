package com.example.thuan.hotel.Model;

public class OrderUser {

    private  String namehotel;
    private  String addressHotel;
    private String DateStartOrder;
    private String DateEndOrder;
    private  int RoomOrder;
    private float totalMoney;

    public OrderUser() {
    }

    public OrderUser(String namehotel, String addressHotel, String dateStartOrder, String dateEndOrder, int roomOrder, float totalMoney) {
        this.namehotel = namehotel;
        this.addressHotel = addressHotel;
        DateStartOrder = dateStartOrder;
        DateEndOrder = dateEndOrder;
        RoomOrder = roomOrder;
        this.totalMoney = totalMoney;
    }

    public String getNamehotel() {
        return namehotel;
    }

    public void setNamehotel(String namehotel) {
        this.namehotel = namehotel;
    }

    public String getAddressHotel() {
        return addressHotel;
    }

    public void setAddressHotel(String addressHotel) {
        this.addressHotel = addressHotel;
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
