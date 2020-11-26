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

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class BookingAPI {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/booking")
    public ResponseEntity<List<BookingDTO>> getAllBooking() {
        BookingMapper bookingMapper = new BookingMapper();
        List<BookingDTO> bookingDTOList = new ArrayList<>();
        List<Booking> bookingList = bookingService.findAllBooking();

        for (Booking booking: bookingList ) {
            BookingDTO bookingDTO =bookingMapper.entityToDTO(booking);
            bookingDTOList.add(bookingDTO);
        }

        return new ResponseEntity<>(bookingDTOList, HttpStatus.OK);
    }
}
