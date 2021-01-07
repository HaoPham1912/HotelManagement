package com.h2.hotelmangement.customer.api;

import com.h2.hotelmangement.Request.BookRoomDTO;
import com.h2.hotelmangement.model.dto.RoomDTO;
import com.h2.hotelmangement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;
import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_CUSTOMER;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PREFIX_CUSTOMER+PREFIX_API)
public class ControllerRoom {
    @Autowired
    private RoomService roomService;

    @GetMapping("room/book")
    public ResponseEntity<?> getAllRoomAvailable(@RequestBody BookRoomDTO bookRoomDTO){
        Optional<Set<RoomDTO>> listRoom = roomService.getListAllRoomAvailable(bookRoomDTO);
        return new ResponseEntity<>(listRoom, HttpStatus.OK);
    }

}
