package com.h2.hotelmangement.api;

import com.h2.hotelmangement.Request.ResponseHistoryBookDTO;
import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.entity.Booking;
import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.model.dto.BookingDTO;
import com.h2.hotelmangement.model.mapper.BookingMapper;
import com.h2.hotelmangement.repository.BookingRepository;
import com.h2.hotelmangement.service.BookingService;
import com.h2.hotelmangement.service.RoomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@RestController
@RequestMapping(PREFIX_API)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookingAPI {

    private BookingMapper bookingMapper = new BookingMapper();

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/booking")
    public ResponseEntity<Map<String, Object>> getAllBooking(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        try {
            Page<Booking> bookingPage = bookingService.getPageBookingPagination(pageNo,size);
            List<Booking> bookingList = bookingPage.getContent();
            List<BookingDTO> bookingDTOList = bookingMapper.convertBookingListEntityToDto(bookingList);
            Map<String, Object> response = new HashMap<>();
            response.put("bookings", bookingDTOList);
            response.put("currentPage", bookingPage.getNumber());
            response.put("totalItems", bookingPage.getTotalElements());
            response.put("totalPages", bookingPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/booking/history/{customerId}")
    public ResponseEntity<?> getHistoryBooking(@PathVariable(value = "customerId") String customerId) {
        if (StringUtils.isNumeric(customerId)) {
            Long custId = Long.valueOf(customerId);
            Optional<Set<ResponseHistoryBookDTO>> listResponseHistoryBookDTO = bookingService.getHistoryBooking(custId);
            return new ResponseEntity<>(listResponseHistoryBookDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
