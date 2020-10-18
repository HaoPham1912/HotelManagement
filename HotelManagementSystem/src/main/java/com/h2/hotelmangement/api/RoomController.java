package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/room")
    public void addRoom(@RequestBody Room room){
        roomService.save(room);
    }

    @PutMapping("/room/{id}")
    public void updateRoom(@RequestBody Room room, @PathVariable Long id){
        Room existRoom = roomService.getRoomById(id);
        if(existRoom != null){
            //existRoom.setThumbnailsRoomList(room.getThumbnailsRoomList());
            roomService.save(room);
        }
    }
}
