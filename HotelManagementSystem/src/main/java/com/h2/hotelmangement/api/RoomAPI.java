package com.h2.hotelmangement.api;


import com.h2.hotelmangement.Request.BookRoomDTO;
import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.model.dto.RoomDTO;
import com.h2.hotelmangement.model.mapper.RoomMapper;
import com.h2.hotelmangement.service.RoomService;
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
    public ResponseEntity<Map<String, Object>> getListRoom(@RequestParam(required = false) String roomCode,
                                           @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                           @RequestParam(value = "size", defaultValue = "3") int size){
        try{
            List<Room> roomList = new ArrayList<>();
            Page<Room>  roomPage;
            if(roomCode == null){
                roomPage = roomService.getPageRoom(pageNo, size);
            }else{
                roomPage = roomService.getPageRoomByCode(roomCode,pageNo,size);
            }
            roomList = roomPage.getContent();
            List<RoomDTO> roomDTOList = roomMapper.convertListRoomEntityToDto(roomList);
            Map<String, Object> response = new HashMap<>();
            response.put("rooms", roomDTOList);
            response.put("currentPage", roomPage.getNumber());
            response.put("totalItems", roomPage.getTotalElements());
            response.put("totalPages", roomPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("room/{code}")
    public ResponseEntity<RoomDTO> getRoomByCode(@PathVariable(value = "code") String roomCode){

        Room room = roomService.getRoomByRoomCode(roomCode);
        RoomDTO roomDTO = roomMapper.roomEntityToDto(room);

        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }

    @GetMapping("room/book")
    public ResponseEntity<?> getAllRoomAvailable(@RequestBody BookRoomDTO bookRoomDTO){
        Optional<Set<RoomDTO>> listRoom = roomService.getListAllRoomAvailable(bookRoomDTO);
        return new ResponseEntity<>(listRoom,HttpStatus.OK);
    }

}
