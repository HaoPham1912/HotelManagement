package com.h2.hotelmangement.Request;

import com.h2.hotelmangement.model.dto.RoomDTO;

import java.util.List;

public class BookingCustomerDTO {
    List<RoomDTO> listRoom;
    Double totalPrice;
    Long checkIn;
    Long checkOut;
    String userName;

    public List<RoomDTO> getListRoom() {
        return listRoom;
    }

    public void setListRoom(List<RoomDTO> listRoom) {
        this.listRoom = listRoom;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Long checkIn) {
        this.checkIn = checkIn;
    }

    public Long getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Long checkOut) {
        this.checkOut = checkOut;
    }
}
