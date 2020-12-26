package com.h2.hotelmangement.service;

import com.h2.hotelmangement.Request.BookRoomDTO;
import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.model.dto.RoomDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoomService {

    List<Room> findAllRoom();

    void save(Room room);

    void delete(Long id);

    Room getRoomById(Long id);

    Room getRoomByRoomCode(String roomCode);
    Optional<Set<RoomDTO>> getListAllRoomAvailable (BookRoomDTO bookRoomDTO);
}
