package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Embeddable
class BookingKeys implements Serializable {

    @Column(name = "roomid")
    private Long roomId;

    @Column(name = "billid")
    private Long billId;

    @Column(name = "bookdate", nullable = false)
    private Date bookdate;

    @Column(name = "checkindate", nullable = false)
    private Date checkindate;

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

    public Date getBookdate() {
        return bookdate;
    }

    public void setBookdate(Date bookdate) {
        this.bookdate = bookdate;
    }

    public Date getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(Date checkindate) {
        this.checkindate = checkindate;
    }
}
