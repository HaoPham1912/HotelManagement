package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.common.util.DateParsing;
import com.h2.hotelmangement.entity.Booking;
import com.h2.hotelmangement.model.dto.BookingDTO;

import java.util.ArrayList;
import java.util.List;

public class BookingMapper {

    public BookingDTO entityToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setBillId(booking.getBookingKey().getBillId());
        bookingDTO.setRoomId(booking.getBookingKey().getRoomId());
        bookingDTO.setBookDate(DateParsing.convertDateToString(booking.getBookingKey().getBookDate()));
        bookingDTO.setCheckinDate(DateParsing.convertDateToString(booking.getBookingKey().getCheckinDate()));
        bookingDTO.setCheckoutDate(DateParsing.convertDateToString(booking.getCheckoutDate()));
        bookingDTO.setPaidPrice(booking.getPaidPrice());
        bookingDTO.setStatus(booking.getStatus());

        return bookingDTO;
    }

    public List<BookingDTO> convertBookingListEntityToDto(List<Booking> bookingList) {
        List<BookingDTO> bookingDTOList = new ArrayList<>();
        for (Booking booking : bookingList) {
            BookingDTO bookingDTO = entityToDTO(booking);
            bookingDTOList.add(bookingDTO);
        }
        return bookingDTOList;
    }
}
