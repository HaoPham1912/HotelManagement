package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Booking;
import com.h2.hotelmangement.model.dto.BookingDTO;

public class BookingMapper {

    public BookingDTO entityToDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setBillId(booking.getBookingKey().getBillId());
        bookingDTO.setRoomId(booking.getBookingKey().getRoomId());
        bookingDTO.setBookDate(booking.getBookingKey().getBookDate());
        bookingDTO.setCheckinDate(booking.getBookingKey().getCheckinDate());
        bookingDTO.setCheckoutDate(booking.getCheckoutDate());
        bookingDTO.setPaidPrice(booking.getPaidPrice());
        bookingDTO.setStatus(booking.isStatus());

        return bookingDTO;
    }
}
