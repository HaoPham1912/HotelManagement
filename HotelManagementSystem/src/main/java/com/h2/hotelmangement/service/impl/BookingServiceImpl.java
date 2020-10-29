package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.Booking;
import com.h2.hotelmangement.repository.BookingRepository;
import com.h2.hotelmangement.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void saveOrUpdate(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getBookingByBookingDate(Long id) {
        return bookingRepository.findBookingByBookingKey_BillId(id);
    }
}
