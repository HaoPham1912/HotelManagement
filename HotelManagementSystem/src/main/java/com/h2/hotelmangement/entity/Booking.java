package com.h2.hotelmangement.entity;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {

    @EmbeddedId
    private BookingKey bookingKey;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "roomid")
    private Room room;

    @ManyToOne
    @MapsId("billId")
    @JoinColumn(name = "billid")
    private Bill bills;

    @Column(name = "checkoutdate", nullable = false)
    private String checkoutDate;

    @Column(name = "paidprice", nullable = false)
    private double paidPrice;

    @Column(name = "status")
    private boolean status;

    public BookingKey getBookingKey() {
        return bookingKey;
    }

    public void setBookingKey(BookingKey bookingKey) {
        this.bookingKey = bookingKey;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Bill getBills() {
        return bills;
    }

    public void setBills(Bill bills) {
        this.bills = bills;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public double getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(double paidPrice) {
        this.paidPrice = paidPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
