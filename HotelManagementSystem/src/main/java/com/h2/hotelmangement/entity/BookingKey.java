package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class BookingKey implements Serializable {

    @Column(name = "roomid")
    private Long roomId;

    @Column(name = "billid")
    private Long billId;

    @Column(name = "bookdate", nullable = false)
    private String bookDate;

    @Column(name = "checkindate", nullable = false)
    private String checkinDate;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingKey that = (BookingKey) o;
        return Objects.equals(roomId, that.roomId) &&
                Objects.equals(billId, that.billId) &&
                Objects.equals(bookDate, that.bookDate) &&
                Objects.equals(checkinDate, that.checkinDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, billId, bookDate, checkinDate);
    }

    @Override
    public String toString() {
        return "BookingKey{" +
                "roomId=" + roomId +
                ", billId=" + billId +
                ", bookDate='" + bookDate + '\'' +
                ", checkinDate='" + checkinDate + '\'' +
                '}';
    }
}
