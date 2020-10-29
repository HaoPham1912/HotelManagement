package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.entity.Booking;
import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.repository.BookingRepository;
import com.h2.hotelmangement.service.BookingService;
import com.h2.hotelmangement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/booking")
    public ResponseEntity<Room> getBookingByBookingDate(@RequestParam(name = "id") Long id){
        System.out.println("param1 " +id);

        List<Booking> bookingsList = bookingService.getBookingByBookingDate(id);

        Booking booking = bookingsList.get(0);

        Long roomid = booking.getRoom().getRoomId();

        Room room= roomService.getRoomById(roomid);

        List<Booking> bookings = room.getBookingList();

        Booking booking1 = bookings.get(0);

        System.out.println("bookings"+booking1.toString());

        System.out.println("==============="+room.getRoomBranch().getBranchId());
        return new ResponseEntity(room, HttpStatus.OK);
    }
}
