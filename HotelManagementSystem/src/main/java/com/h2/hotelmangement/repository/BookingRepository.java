package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository<BookingKey> extends JpaRepository<Booking, BookingKey> {

}
