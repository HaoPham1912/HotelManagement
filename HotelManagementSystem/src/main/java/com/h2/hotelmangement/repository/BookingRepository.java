package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Booking;
import com.h2.hotelmangement.entity.BookingKey;
import com.h2.hotelmangement.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking, BookingKey> {
    List<Booking> findBookingByBookingKey_BookDate(String bookDate);

    @Query("SELECT b.room from Booking b where b.bookingKey.checkinDate > :checkOut and b.checkoutDate < :checkIn ")
    Optional<Set<Room>> findRoomAvailableTime(Date checkIn, Date checkOut);

    Optional<Booking> findByBills_Billid(String billId);

    Set<Booking> findBookingByBookingKey_BillId(Long id);

    Long deleteBookingByBills_BillidAndRoom_RoomId(Long billId, Long roomId);

}
