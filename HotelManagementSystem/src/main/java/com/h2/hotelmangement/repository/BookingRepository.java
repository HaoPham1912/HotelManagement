package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Booking;
import com.h2.hotelmangement.entity.BookingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, BookingKey> {
    List<Booking> findBookingByBookingKey_BillId(Long id);
}
