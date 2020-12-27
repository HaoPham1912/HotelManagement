package com.h2.hotelmangement.Request;

import com.h2.hotelmangement.model.dto.BillDTO;
import com.h2.hotelmangement.model.dto.BookingDTO;
import com.h2.hotelmangement.model.dto.RoomDTO;

public class ResponseHistoryBookDTO {
    private RoomDTO roomDTO;
    private BookingDTO bookingDTO;
    private BillDTO billDTO;

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }

    public BookingDTO getBookingDTO() {
        return bookingDTO;
    }

    public void setBookingDTO(BookingDTO bookingDTO) {
        this.bookingDTO = bookingDTO;
    }

    public BillDTO getBillDTO() {
        return billDTO;
    }

    public void setBillDTO(BillDTO billDTO) {
        this.billDTO = billDTO;
    }
}
