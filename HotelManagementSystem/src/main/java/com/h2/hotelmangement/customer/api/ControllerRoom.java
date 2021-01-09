package com.h2.hotelmangement.customer.api;

import com.h2.hotelmangement.Request.BookRoomDTO;
import com.h2.hotelmangement.Request.BookingCustomerDTO;
import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.model.dto.BookingDTO;
import com.h2.hotelmangement.model.dto.RoomDTO;
import com.h2.hotelmangement.model.mapper.RoomMapper;
import com.h2.hotelmangement.service.BookingService;
import com.h2.hotelmangement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;
import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_CUSTOMER;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PREFIX_CUSTOMER+PREFIX_API)
public class ControllerRoom {
    @Autowired
    private RoomService roomService;

    public RoomMapper roomMapper = new RoomMapper();

    @Autowired
    private BookingService bookingService;

    @PostMapping("room/book")
    public ResponseEntity<?> getAllRoomAvailable(@RequestBody BookRoomDTO bookRoomDTO){
        Optional<Set<RoomDTO>> listRoom = roomService.getListAllRoomAvailable(bookRoomDTO);
        return new ResponseEntity<>(listRoom, HttpStatus.OK);
    }

    @PostMapping("room")
    public ResponseEntity<Map<String, Object>> getListRoom(@RequestBody BookRoomDTO bookRoomDTO) {
        try {
            List<Room> roomList = new ArrayList<>();
            Page<Room> roomPage;
            roomPage = roomService.getListAllRoomAvailable2(bookRoomDTO);
            roomList = roomPage.getContent();
            List<RoomDTO> roomDTOList = roomMapper.convertListRoomEntityToDto(roomList);
            Map<String, Object> response = new HashMap<>();
            response.put("rooms", roomDTOList);
            response.put("currentPage", roomPage.getNumber());
            response.put("totalItems", roomPage.getTotalElements());
            response.put("totalPages", roomPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/booking")
    public ResponseEntity<Map<String, Object>> bookRoomForCustomer(@RequestBody BookingCustomerDTO params) throws Exception {
        Map<String, Object> response = new HashMap<>();
        Optional<List<BookingDTO>> bookingDTOList = bookingService.createBookingCustomer(params);
        if(bookingDTOList.isPresent()){
            response.put("status", "success");
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
        response.put("status","failed");
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

}
