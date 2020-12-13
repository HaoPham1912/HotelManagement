package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.entity.Booking;
import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.model.dto.BookingDTO;
import com.h2.hotelmangement.model.mapper.BookingMapper;
import com.h2.hotelmangement.repository.BookingRepository;
import com.h2.hotelmangement.service.BookingService;
import com.h2.hotelmangement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@RestController
@RequestMapping(PREFIX_API)
@CrossOrigin(origins = "http://localhost:8080")
public class BookingAPI {

    private BookingMapper bookingMapper = new BookingMapper();

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/booking")
    public ResponseEntity<List<BookingDTO>> getAllBooking() {
        List<Booking> bookingList = bookingService.findAllBooking();
        List<BookingDTO> bookingDTOList = bookingMapper.convertBookingListEntityToDto(bookingList);
        return new ResponseEntity<>(bookingDTOList, HttpStatus.OK);
    }
}
