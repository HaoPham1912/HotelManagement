package com.h2.hotelmangement.api;

import com.h2.hotelmangement.Request.BookRoomDTO;
import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.model.dto.RoomDTO;
import com.h2.hotelmangement.model.mapper.RoomMapper;
import com.h2.hotelmangement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@RestController
@RequestMapping(PREFIX_API)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoomAPI {

    @Autowired
    private RoomService roomService;

    @PostMapping("/room")
    public void addRoom(@RequestBody Room room){
        roomService.save(room);
    }

   public  RoomMapper roomMapper = new RoomMapper();

    @PutMapping("/room/{id}")
    public void updateRoom(@RequestBody Room room, @PathVariable Long id){
        Room existRoom = roomService.getRoomById(id);
        if(existRoom != null){
            //existRoom.setThumbnailsRoomList(room.getThumbnailsRoomList());
            roomService.save(room);
        }
    }

    @GetMapping("room")
    public ResponseEntity<List<RoomDTO>> getListRoom(){
        List<Room> roomList = roomService.findAllRoom();
        List<RoomDTO> roomDTOList = new ArrayList<>();

        for (Room room : roomList) {
            RoomDTO roomDTO = roomMapper.roomEntityToDto(room);
            roomDTOList.add(roomDTO);
        }
        return new ResponseEntity<>(roomDTOList, HttpStatus.OK);
    }

    @GetMapping("room/{code}")
    public ResponseEntity<RoomDTO> getRoomByCode(@PathVariable(value = "code") String roomCode){

        Room room = roomService.getRoomByRoomCode(roomCode);
        RoomDTO roomDTO = roomMapper.roomEntityToDto(room);

        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }

    @GetMapping("room/book")
    public ResponseEntity<?> getAllRoomAvailable(@RequestBody BookRoomDTO bookRoomDTO){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
