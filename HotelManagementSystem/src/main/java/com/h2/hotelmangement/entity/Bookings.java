package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bookings")
public class Bookings {

    @EmbeddedId
    BookingKeys bookingKeys;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "roomid")
    private Rooms rooms;

    @ManyToOne
    @MapsId("billId")
    @JoinColumn(name = "billid")
    private Bills bills;

    @Column(name = "checkoutdate", nullable = false)
    private Date checkoutdate;

    @Column(name = "paidprice", nullable = false)
    private double paidprice;

    @Column(name = "status")
    private boolean status;

    public BookingKeys getBookingKeys() {
        return bookingKeys;
    }

    public void setBookingKeys(BookingKeys bookingKeys) {
        this.bookingKeys = bookingKeys;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public Bills getBills() {
        return bills;
    }

    public void setBills(Bills bills) {
        this.bills = bills;
    }

    public Date getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(Date checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    public double getPaidprice() {
        return paidprice;
    }

    public void setPaidprice(double paidprice) {
        this.paidprice = paidprice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
