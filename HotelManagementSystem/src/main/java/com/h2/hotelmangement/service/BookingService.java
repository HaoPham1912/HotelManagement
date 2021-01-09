package com.h2.hotelmangement.service;

import com.h2.hotelmangement.Request.BookingCustomerDTO;
import com.h2.hotelmangement.Request.ResponseHistoryBookDTO;
import com.h2.hotelmangement.entity.Booking;
import com.h2.hotelmangement.model.dto.BookingDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookingService {
    void saveOrUpdate(Booking booking);

    List<Booking> findAllBooking();

    List<Booking> getBookingByBookingDate(String bookingDate);

    Optional<Set<ResponseHistoryBookDTO>> getHistoryBooking(Long customerId);

    Page<Booking> getPageBookingPagination(int pageNo, int pageSize);

    Set<Booking> getBookingByBillId(Long id);

    Long deleteBooking(Long billId, Long roomId) throws Exception;

    Booking getBookingInfor(Long billId, Long roomId);

    Optional<List<BookingDTO>> createBookingCustomer(BookingCustomerDTO bookingCustomerDTO) throws Exception;
}
