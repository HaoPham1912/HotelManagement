package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Embeddable
class BookingKey implements Serializable {

    @Column(name = "roomid")
    private Long roomId;

    @Column(name = "billid")
    private Long billId;

    @Column(name = "bookdate", nullable = false)
    private String bookdate;

    @Column(name = "checkindate", nullable = false)
    private String checkindate;

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

    public String getBookdate() {
        return bookdate;
    }

    public void setBookdate(String bookdate) {
        this.bookdate = bookdate;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }
}
