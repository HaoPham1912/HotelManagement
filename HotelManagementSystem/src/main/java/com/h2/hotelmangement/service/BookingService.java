package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Booking;

import java.util.List;

public interface BookingService {
    void saveOrUpdate(Booking booking);

    List<Booking> findAllBooking();

    List<Booking> getBookingByBookingDate(String bookingDate);



}
