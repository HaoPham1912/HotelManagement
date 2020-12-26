package com.h2.hotelmangement.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bookings")
@DynamicInsert
@DynamicUpdate
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
    private Date checkoutDate;

    @Column(name = "paidprice", nullable = false)
    private double paidPrice;

    @Column(name = "status", columnDefinition = "boolean default true")
    private Boolean status;

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

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public double getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(double paidPrice) {
        this.paidPrice = paidPrice;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingKey=" + bookingKey.toString() +
                ", room=" + room +
                ", bills=" + bills +
                ", checkoutDate='" + checkoutDate + '\'' +
                ", paidPrice=" + paidPrice +
                ", status=" + status +
                '}';
    }
}
